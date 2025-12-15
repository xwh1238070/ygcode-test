package com.ygsoft.user.management.service.query;

import com.ygsoft.user.management.dao.DepartmentDao;
import com.ygsoft.user.management.dao.UserDao;
import com.ygsoft.user.management.domain.po.DepartmentPO;
import com.ygsoft.user.management.domain.transfer.DepartmentTransfer;
import com.ygsoft.user.management.domain.vo.DepartmentQueryVO;
import com.ygsoft.user.management.domain.vo.DepartmentTreeVO;
import com.ygsoft.user.management.domain.vo.DepartmentVO;
import com.ygsoft.user.management.infrastructure.constant.DepartmentConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门查询服务实现类
 * 
 * @author developer
 * @date 2024-12-15
 */
@Service
public class DepartmentQueryServiceImpl implements DepartmentQueryService {
    
    @Autowired
    private DepartmentDao departmentDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private DepartmentTransfer departmentTransfer;
    
    @Override
    public List<DepartmentVO> getDepartmentList(DepartmentQueryVO queryVO) {
        // 1. 构建查询条件
        Specification<DepartmentPO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // 部门名称模糊查询
            if (queryVO.getDeptName() != null && !queryVO.getDeptName().trim().isEmpty()) {
                predicates.add(cb.like(root.get("deptName"), "%" + queryVO.getDeptName() + "%"));
            }
            
            // 部门编码精确查询
            if (queryVO.getDeptCode() != null && !queryVO.getDeptCode().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("deptCode"), queryVO.getDeptCode()));
            }
            
            // 父部门ID
            if (queryVO.getParentId() != null && !queryVO.getParentId().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("parentId"), queryVO.getParentId()));
            }
            
            // 状态
            if (queryVO.getStatus() != null && !queryVO.getStatus().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("status"), queryVO.getStatus()));
            }
            
            // 部门层级
            if (queryVO.getDeptLevel() != null) {
                predicates.add(cb.equal(root.get("deptLevel"), queryVO.getDeptLevel()));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        
        // 2. 分页查询
        List<DepartmentPO> departmentList;
        if (queryVO.getPageSize() != null && queryVO.getPageIndex() != null) {
            int pageIndex = queryVO.getPageIndex() - 1; // 页码从0开始
            int pageSize = queryVO.getPageSize();
            Pageable pageable = PageRequest.of(pageIndex, pageSize, 
                Sort.by(Sort.Direction.ASC, "deptLevel", "sortOrder"));
            Page<DepartmentPO> page = departmentDao.findAll(spec, pageable);
            departmentList = page.getContent();
        } else {
            departmentList = departmentDao.findAll(spec, 
                Sort.by(Sort.Direction.ASC, "deptLevel", "sortOrder"));
        }
        
        // 3. PO转VO
        List<DepartmentVO> result = new ArrayList<>();
        for (DepartmentPO po : departmentList) {
            DepartmentVO vo = departmentTransfer.poToVo(po);
            
            // 设置父部门名称
            if (po.getParentId() != null) {
                DepartmentPO parentDept = departmentDao.findById(po.getParentId()).orElse(null);
                if (parentDept != null) {
                    vo.setParentName(parentDept.getDeptName());
                }
            }
            
            // 统计用户数量
            long userCount = userDao.countByDepartmentId(po.getDeptId());
            vo.setUserCount((int) userCount);
            
            result.add(vo);
        }
        
        return result;
    }
    
    @Override
    public List<DepartmentTreeVO> getDepartmentTree(String status) {
        // 1. 查询所有部门
        List<DepartmentPO> allDepartments;
        if (status != null && !status.trim().isEmpty()) {
            allDepartments = departmentDao.findByStatusOrderByDeptLevelAscSortOrderAsc(status);
        } else {
            allDepartments = departmentDao.findAllByOrderByDeptLevelAscSortOrderAsc();
        }
        
        // 2. PO转TreeVO
        List<DepartmentTreeVO> allTreeVOs = allDepartments.stream()
            .map(po -> {
                DepartmentTreeVO treeVO = departmentTransfer.poToTreeVo(po);
                // 统计用户数量
                long userCount = userDao.countByDepartmentId(po.getDeptId());
                treeVO.setUserCount((int) userCount);
                return treeVO;
            })
            .collect(Collectors.toList());
        
        // 3. 构建树形结构
        return buildTree(allTreeVOs);
    }
    
    @Override
    public long getDepartmentCount(DepartmentQueryVO queryVO) {
        // 构建查询条件
        Specification<DepartmentPO> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (queryVO.getDeptName() != null && !queryVO.getDeptName().trim().isEmpty()) {
                predicates.add(cb.like(root.get("deptName"), "%" + queryVO.getDeptName() + "%"));
            }
            
            if (queryVO.getDeptCode() != null && !queryVO.getDeptCode().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("deptCode"), queryVO.getDeptCode()));
            }
            
            if (queryVO.getParentId() != null && !queryVO.getParentId().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("parentId"), queryVO.getParentId()));
            }
            
            if (queryVO.getStatus() != null && !queryVO.getStatus().trim().isEmpty()) {
                predicates.add(cb.equal(root.get("status"), queryVO.getStatus()));
            }
            
            if (queryVO.getDeptLevel() != null) {
                predicates.add(cb.equal(root.get("deptLevel"), queryVO.getDeptLevel()));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        
        return departmentDao.count(spec);
    }
    
    @Override
    public DepartmentVO getDepartmentById(String deptId) {
        if (deptId == null || deptId.trim().isEmpty()) {
            return null;
        }
        
        DepartmentPO po = departmentDao.findById(deptId).orElse(null);
        if (po == null) {
            return null;
        }
        
        DepartmentVO vo = departmentTransfer.poToVo(po);
        
        // 设置父部门名称
        if (po.getParentId() != null) {
            DepartmentPO parentDept = departmentDao.findById(po.getParentId()).orElse(null);
            if (parentDept != null) {
                vo.setParentName(parentDept.getDeptName());
            }
        }
        
        // 统计用户数量
        long userCount = userDao.countByDepartmentId(po.getDeptId());
        vo.setUserCount((int) userCount);
        
        return vo;
    }
    
    @Override
    public List<DepartmentVO> getChildDepartments(String parentId) {
        List<DepartmentPO> children = departmentDao.findByParentIdOrderBySortOrderAsc(parentId);
        
        return children.stream()
            .map(po -> {
                DepartmentVO vo = departmentTransfer.poToVo(po);
                long userCount = userDao.countByDepartmentId(po.getDeptId());
                vo.setUserCount((int) userCount);
                return vo;
            })
            .collect(Collectors.toList());
    }
    
    @Override
    public int getDepartmentUserCount(String deptId) {
        if (deptId == null || deptId.trim().isEmpty()) {
            return 0;
        }
        
        long count = userDao.countByDepartmentId(deptId);
        return (int) count;
    }
    
    /**
     * 构建树形结构
     */
    private List<DepartmentTreeVO> buildTree(List<DepartmentTreeVO> allNodes) {
        // 1. 创建ID到节点的映射
        Map<String, DepartmentTreeVO> nodeMap = new HashMap<>();
        for (DepartmentTreeVO node : allNodes) {
            nodeMap.put(node.getDeptId(), node);
            node.setChildren(new ArrayList<>());
        }
        
        // 2. 构建父子关系
        List<DepartmentTreeVO> rootNodes = new ArrayList<>();
        for (DepartmentTreeVO node : allNodes) {
            if (node.getParentId() == null || node.getParentId().trim().isEmpty()) {
                // 顶级节点
                rootNodes.add(node);
            } else {
                // 子节点，添加到父节点的children中
                DepartmentTreeVO parent = nodeMap.get(node.getParentId());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }
        
        return rootNodes;
    }
}
