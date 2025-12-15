package com.ygsoft.user.management.domain.transfer;

import com.ygsoft.user.management.domain.bo.UserBO;
import com.ygsoft.user.management.domain.po.UserPO;
import com.ygsoft.user.management.domain.vo.UserVO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户模型转换类
 * 
 * 提供不同模型之间的转换方法
 * 
 * @author developer
 * @date 2024-12-15
 */
public class UserTransfer {
    
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * VO转BO
     * 
     * @param vo 视图对象
     * @return 业务对象
     */
    public static UserBO voToBo(UserVO vo) {
        if (vo == null) {
            return null;
        }
        
        UserBO bo = new UserBO();
        bo.setUserId(vo.getUserId());
        bo.setUsername(vo.getUsername());
        bo.setRealName(vo.getRealName());
        bo.setDepartment(vo.getDepartment());
        bo.setRole(vo.getRole());
        bo.setPhone(vo.getPhone());
        bo.setEmail(vo.getEmail());
        bo.setStatus(vo.getStatus());
        
        return bo;
    }
    
    /**
     * BO转PO
     * 
     * @param bo 业务对象
     * @return 持久化对象
     */
    public static UserPO boToPo(UserBO bo) {
        if (bo == null) {
            return null;
        }
        
        UserPO po = new UserPO();
        po.setUserId(bo.getUserId());
        po.setUsername(bo.getUsername());
        po.setPassword(bo.getPassword());
        po.setRealName(bo.getRealName());
        po.setDepartmentId(bo.getDepartmentId());
        po.setDepartment(bo.getDepartment());
        po.setRoleId(bo.getRoleId());
        po.setRole(bo.getRole());
        po.setPhone(bo.getPhone());
        po.setEmail(bo.getEmail());
        po.setStatus(bo.getStatus());
        po.setCreateTime(bo.getCreateTime());
        po.setUpdateTime(bo.getUpdateTime());
        po.setLastLoginTime(bo.getLastLoginTime());
        po.setCreator(bo.getCreator());
        po.setUpdater(bo.getUpdater());
        
        return po;
    }
    
    /**
     * PO转BO
     * 
     * @param po 持久化对象
     * @return 业务对象
     */
    public static UserBO poToBo(UserPO po) {
        if (po == null) {
            return null;
        }
        
        UserBO bo = new UserBO();
        bo.setUserId(po.getUserId());
        bo.setUsername(po.getUsername());
        bo.setPassword(po.getPassword());
        bo.setRealName(po.getRealName());
        bo.setDepartmentId(po.getDepartmentId());
        bo.setDepartment(po.getDepartment());
        bo.setRoleId(po.getRoleId());
        bo.setRole(po.getRole());
        bo.setPhone(po.getPhone());
        bo.setEmail(po.getEmail());
        bo.setStatus(po.getStatus());
        bo.setCreateTime(po.getCreateTime());
        bo.setUpdateTime(po.getUpdateTime());
        bo.setLastLoginTime(po.getLastLoginTime());
        bo.setCreator(po.getCreator());
        bo.setUpdater(po.getUpdater());
        
        return bo;
    }
    
    /**
     * BO转VO
     * 
     * @param bo 业务对象
     * @return 视图对象
     */
    public static UserVO boToVo(UserBO bo) {
        if (bo == null) {
            return null;
        }
        
        UserVO vo = new UserVO();
        vo.setUserId(bo.getUserId());
        vo.setUsername(bo.getUsername());
        vo.setRealName(bo.getRealName());
        vo.setDepartment(bo.getDepartment());
        vo.setRole(bo.getRole());
        vo.setPhone(bo.getPhone());
        vo.setEmail(bo.getEmail());
        
        // 状态转换：1->启用，0->禁用
        if ("1".equals(bo.getStatus())) {
            vo.setStatus("启用");
        } else if ("0".equals(bo.getStatus())) {
            vo.setStatus("禁用");
        }
        
        // 日期格式化
        vo.setCreateTime(formatDate(bo.getCreateTime()));
        vo.setLastLoginTime(formatDate(bo.getLastLoginTime()));
        
        return vo;
    }
    
    /**
     * PO转VO
     * 
     * @param po 持久化对象
     * @return 视图对象
     */
    public static UserVO poToVo(UserPO po) {
        if (po == null) {
            return null;
        }
        
        return boToVo(poToBo(po));
    }
    
    /**
     * 格式化日期
     * 
     * @param date 日期对象
     * @return 格式化后的字符串
     */
    private static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }
}
