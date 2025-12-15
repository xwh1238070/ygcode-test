package com.ygsoft.user.management.domain.transfer;

import com.ygsoft.user.management.domain.bo.DepartmentBO;
import com.ygsoft.user.management.domain.po.DepartmentPO;
import com.ygsoft.user.management.domain.vo.DepartmentTreeVO;
import com.ygsoft.user.management.domain.vo.DepartmentVO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 部门模型转换类
 * 
 * 提供PO、BO、VO之间的转换方法
 * 
 * @author developer
 * @date 2024-12-15
 */
@Component
public class DepartmentTransfer {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /**
     * VO转BO
     */
    public DepartmentBO voToBo(DepartmentVO vo) {
        if (vo == null) {
            return null;
        }
        
        DepartmentBO bo = new DepartmentBO();
        bo.setDeptId(vo.getDeptId());
        bo.setDeptCode(vo.getDeptCode());
        bo.setDeptName(vo.getDeptName());
        bo.setParentId(vo.getParentId());
        bo.setDeptLevel(vo.getDeptLevel());
        bo.setSortOrder(vo.getSortOrder());
        bo.setLeader(vo.getLeader());
        bo.setPhone(vo.getPhone());
        bo.setEmail(vo.getEmail());
        bo.setStatus(vo.getStatus());
        bo.setDescription(vo.getDescription());
        
        return bo;
    }
    
    /**
     * BO转PO
     */
    public DepartmentPO boToPo(DepartmentBO bo) {
        if (bo == null) {
            return null;
        }
        
        DepartmentPO po = new DepartmentPO();
        po.setDeptId(bo.getDeptId());
        po.setDeptCode(bo.getDeptCode());
        po.setDeptName(bo.getDeptName());
        po.setParentId(bo.getParentId());
        po.setDeptLevel(bo.getDeptLevel());
        po.setDeptPath(bo.getDeptPath());
        po.setSortOrder(bo.getSortOrder());
        po.setLeader(bo.getLeader());
        po.setPhone(bo.getPhone());
        po.setEmail(bo.getEmail());
        po.setStatus(bo.getStatus());
        po.setDescription(bo.getDescription());
        po.setCreateTime(bo.getCreateTime());
        po.setUpdateTime(bo.getUpdateTime());
        po.setCreator(bo.getCreator());
        po.setUpdater(bo.getUpdater());
        
        return po;
    }
    
    /**
     * PO转BO
     */
    public DepartmentBO poToBo(DepartmentPO po) {
        if (po == null) {
            return null;
        }
        
        DepartmentBO bo = new DepartmentBO();
        bo.setDeptId(po.getDeptId());
        bo.setDeptCode(po.getDeptCode());
        bo.setDeptName(po.getDeptName());
        bo.setParentId(po.getParentId());
        bo.setDeptLevel(po.getDeptLevel());
        bo.setDeptPath(po.getDeptPath());
        bo.setSortOrder(po.getSortOrder());
        bo.setLeader(po.getLeader());
        bo.setPhone(po.getPhone());
        bo.setEmail(po.getEmail());
        bo.setStatus(po.getStatus());
        bo.setDescription(po.getDescription());
        bo.setCreateTime(po.getCreateTime());
        bo.setUpdateTime(po.getUpdateTime());
        bo.setCreator(po.getCreator());
        bo.setUpdater(po.getUpdater());
        
        return bo;
    }
    
    /**
     * BO转VO
     */
    public DepartmentVO boToVo(DepartmentBO bo) {
        if (bo == null) {
            return null;
        }
        
        DepartmentVO vo = new DepartmentVO();
        vo.setDeptId(bo.getDeptId());
        vo.setDeptCode(bo.getDeptCode());
        vo.setDeptName(bo.getDeptName());
        vo.setParentId(bo.getParentId());
        vo.setDeptLevel(bo.getDeptLevel());
        vo.setSortOrder(bo.getSortOrder());
        vo.setLeader(bo.getLeader());
        vo.setPhone(bo.getPhone());
        vo.setEmail(bo.getEmail());
        vo.setStatus(bo.getStatus());
        vo.setStatusText("1".equals(bo.getStatus()) ? "启用" : "禁用");
        vo.setDescription(bo.getDescription());
        
        if (bo.getCreateTime() != null) {
            vo.setCreateTime(DATE_FORMAT.format(bo.getCreateTime()));
        }
        
        return vo;
    }
    
    /**
     * PO转VO
     */
    public DepartmentVO poToVo(DepartmentPO po) {
        if (po == null) {
            return null;
        }
        
        DepartmentVO vo = new DepartmentVO();
        vo.setDeptId(po.getDeptId());
        vo.setDeptCode(po.getDeptCode());
        vo.setDeptName(po.getDeptName());
        vo.setParentId(po.getParentId());
        vo.setDeptLevel(po.getDeptLevel());
        vo.setSortOrder(po.getSortOrder());
        vo.setLeader(po.getLeader());
        vo.setPhone(po.getPhone());
        vo.setEmail(po.getEmail());
        vo.setStatus(po.getStatus());
        vo.setStatusText("1".equals(po.getStatus()) ? "启用" : "禁用");
        vo.setDescription(po.getDescription());
        
        if (po.getCreateTime() != null) {
            vo.setCreateTime(DATE_FORMAT.format(po.getCreateTime()));
        }
        
        return vo;
    }
    
    /**
     * PO转TreeVO
     */
    public DepartmentTreeVO poToTreeVo(DepartmentPO po) {
        if (po == null) {
            return null;
        }
        
        DepartmentTreeVO treeVO = new DepartmentTreeVO();
        treeVO.setDeptId(po.getDeptId());
        treeVO.setDeptCode(po.getDeptCode());
        treeVO.setDeptName(po.getDeptName());
        treeVO.setParentId(po.getParentId());
        treeVO.setDeptLevel(po.getDeptLevel());
        treeVO.setSortOrder(po.getSortOrder());
        treeVO.setStatus(po.getStatus());
        
        return treeVO;
    }
    
    /**
     * 格式化日期
     */
    public String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMAT.format(date);
    }
}
