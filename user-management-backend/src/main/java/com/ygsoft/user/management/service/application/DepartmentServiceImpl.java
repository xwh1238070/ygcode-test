package com.ygsoft.user.management.service.application;

import com.ygsoft.user.management.dao.DepartmentDao;
import com.ygsoft.user.management.dao.UserDao;
import com.ygsoft.user.management.domain.bo.DepartmentBO;
import com.ygsoft.user.management.domain.po.DepartmentPO;
import com.ygsoft.user.management.domain.transfer.DepartmentTransfer;
import com.ygsoft.user.management.domain.vo.DepartmentBatchVO;
import com.ygsoft.user.management.domain.vo.DepartmentVO;
import com.ygsoft.user.management.infrastructure.constant.DepartmentConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

/**
 * 部门业务服务实现类
 * 
 * @author developer
 * @date 2024-12-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private DepartmentTransfer departmentTransfer;
    
    private static final SecureRandom RANDOM = new SecureRandom();
    
    @Override
    public String createDepartment(DepartmentVO departmentVO) {
        // 1. 参数验证
        validateDepartmentVO(departmentVO, true);
        
        // 2. 检查部门编码是否已存在
        DepartmentPO existingDept = departmentDao.findByDeptCode(departmentVO.getDeptCode());
        if (existingDept != null) {
            throw new IllegalArgumentException("部门编码已存在");
        }
        
        // 3. VO转BO
        DepartmentBO departmentBO = departmentTransfer.voToBo(departmentVO);
        
        // 4. 生成部门ID
        String deptId = generateDeptId();
        departmentBO.setDeptId(deptId);
        
        // 5. 处理父部门和层级
        if (departmentVO.getParentId() != null && !departmentVO.getParentId().trim().isEmpty()) {
            DepartmentPO parentDept = departmentDao.findById(departmentVO.getParentId()).orElse(null);
            if (parentDept == null) {
                throw new IllegalArgumentException("父部门不存在");
            }
            
            // 检查层级限制
            int newLevel = parentDept.getDeptLevel() + 1;
            if (newLevel > DepartmentConstant.MAX_DEPT_LEVEL) {
                throw new IllegalArgumentException("部门层级超过限制（最多" + DepartmentConstant.MAX_DEPT_LEVEL + "级）");
            }
            
            departmentBO.setDeptLevel(newLevel);
            departmentBO.setDeptPath(parentDept.getDeptPath() + DepartmentConstant.DEPT_PATH_SEPARATOR + deptId);
        } else {
            // 顶级部门
            departmentBO.setDeptLevel(DepartmentConstant.TOP_DEPT_LEVEL);
            departmentBO.setDeptPath(DepartmentConstant.DEPT_PATH_SEPARATOR + deptId);
            departmentBO.setParentId(null);
        }
        
        // 6. 设置默认值
        if (departmentBO.getSortOrder() == null) {
            departmentBO.setSortOrder(DepartmentConstant.DEFAULT_SORT_ORDER);
        }
        departmentBO.setStatus(DepartmentConstant.STATUS_ENABLED);
        departmentBO.setCreateTime(new Date());
        
        // 7. BO转PO并保存
        DepartmentPO departmentPO = departmentTransfer.boToPo(departmentBO);
        departmentDao.save(departmentPO);
        
        return deptId;
    }
    
    @Override
    public boolean updateDepartment(DepartmentVO departmentVO) {
        // 1. 参数验证
        if (departmentVO == null || departmentVO.getDeptId() == null) {
            throw new IllegalArgumentException("部门ID不能为空");
        }
        
        // 2. 查询原部门信息
        DepartmentPO existingDept = departmentDao.findById(departmentVO.getDeptId()).orElse(null);
        if (existingDept == null) {
            throw new IllegalArgumentException("部门不存在");
        }
        
        // 3. 更新字段
        if (departmentVO.getDeptName() != null) {
            existingDept.setDeptName(departmentVO.getDeptName());
        }
        if (departmentVO.getSortOrder() != null) {
            existingDept.setSortOrder(departmentVO.getSortOrder());
        }
        if (departmentVO.getLeader() != null) {
            existingDept.setLeader(departmentVO.getLeader());
        }
        if (departmentVO.getPhone() != null) {
            existingDept.setPhone(departmentVO.getPhone());
        }
        if (departmentVO.getEmail() != null) {
            existingDept.setEmail(departmentVO.getEmail());
        }
        if (departmentVO.getDescription() != null) {
            existingDept.setDescription(departmentVO.getDescription());
        }
        
        existingDept.setUpdateTime(new Date());
        
        // 4. 保存更新
        departmentDao.save(existingDept);
        
        // 5. 如果部门名称变更，同步更新用户表中的部门名称
        if (departmentVO.getDeptName() != null && !departmentVO.getDeptName().equals(existingDept.getDeptName())) {
            userDao.updateDepartmentNameByDepartmentId(departmentVO.getDeptId(), departmentVO.getDeptName());
        }
        
        return true;
    }
    
    @Override
    public int deleteDepartments(DepartmentBatchVO batchVO) {
        // 1. 参数验证
        if (batchVO == null || batchVO.getDeptIds() == null || batchVO.getDeptIds().isEmpty()) {
            throw new IllegalArgumentException("部门ID列表不能为空");
        }
        
        List<String> deptIds = batchVO.getDeptIds();
        if (deptIds.size() > DepartmentConstant.MAX_BATCH_SIZE) {
            throw new IllegalArgumentException("批量操作数量超过限制（最多" + DepartmentConstant.MAX_BATCH_SIZE + "个）");
        }
        
        int deletedCount = 0;
        
        // 2. 逐个删除
        for (String deptId : deptIds) {
            try {
                // 检查是否有子部门
                long childCount = departmentDao.countByParentId(deptId);
                if (childCount > 0) {
                    continue; // 跳过有子部门的
                }
                
                // 检查是否有关联用户
                long userCount = userDao.countByDepartmentId(deptId);
                if (userCount > 0) {
                    continue; // 跳过有用户的
                }
                
                // 删除部门
                departmentDao.deleteById(deptId);
                deletedCount++;
            } catch (Exception e) {
                // 继续处理下一个
            }
        }
        
        return deletedCount;
    }
    
    @Override
    public int enableDepartments(DepartmentBatchVO batchVO) {
        return updateDepartmentStatus(batchVO, DepartmentConstant.STATUS_ENABLED);
    }
    
    @Override
    public int disableDepartments(DepartmentBatchVO batchVO) {
        return updateDepartmentStatus(batchVO, DepartmentConstant.STATUS_DISABLED);
    }
    
    /**
     * 更新部门状态
     */
    private int updateDepartmentStatus(DepartmentBatchVO batchVO, String status) {
        // 1. 参数验证
        if (batchVO == null || batchVO.getDeptIds() == null || batchVO.getDeptIds().isEmpty()) {
            throw new IllegalArgumentException("部门ID列表不能为空");
        }
        
        List<String> deptIds = batchVO.getDeptIds();
        if (deptIds.size() > DepartmentConstant.MAX_BATCH_SIZE) {
            throw new IllegalArgumentException("批量操作数量超过限制（最多" + DepartmentConstant.MAX_BATCH_SIZE + "个）");
        }
        
        // 2. 查询部门列表
        List<DepartmentPO> departments = departmentDao.findByDeptIdIn(deptIds);
        
        // 3. 更新状态
        int updatedCount = 0;
        for (DepartmentPO dept : departments) {
            dept.setStatus(status);
            dept.setUpdateTime(new Date());
            departmentDao.save(dept);
            updatedCount++;
        }
        
        return updatedCount;
    }
    
    /**
     * 验证部门VO
     */
    private void validateDepartmentVO(DepartmentVO vo, boolean isCreate) {
        if (vo == null) {
            throw new IllegalArgumentException("部门信息不能为空");
        }
        
        if (isCreate) {
            // 创建时必填字段
            if (vo.getDeptCode() == null || vo.getDeptCode().trim().isEmpty()) {
                throw new IllegalArgumentException("部门编码不能为空");
            }
            if (vo.getDeptName() == null || vo.getDeptName().trim().isEmpty()) {
                throw new IllegalArgumentException("部门名称不能为空");
            }
            
            // 验证部门编码格式
            String deptCode = vo.getDeptCode().trim();
            if (deptCode.length() < DepartmentConstant.DEPT_CODE_MIN_LENGTH || 
                deptCode.length() > DepartmentConstant.DEPT_CODE_MAX_LENGTH) {
                throw new IllegalArgumentException("部门编码长度必须在" + 
                    DepartmentConstant.DEPT_CODE_MIN_LENGTH + "-" + 
                    DepartmentConstant.DEPT_CODE_MAX_LENGTH + "个字符之间");
            }
            
            if (!deptCode.matches(DepartmentConstant.DEPT_CODE_PATTERN)) {
                throw new IllegalArgumentException("部门编码只能包含字母、数字和下划线");
            }
        }
    }
    
    /**
     * 生成部门ID
     */
    private String generateDeptId() {
        long timestamp = System.currentTimeMillis();
        int randomNum = RANDOM.nextInt(1000);
        return DepartmentConstant.DEPT_ID_PREFIX + timestamp + String.format("%03d", randomNum);
    }
}
