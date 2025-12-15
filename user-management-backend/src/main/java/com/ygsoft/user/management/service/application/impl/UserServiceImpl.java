package com.ygsoft.user.management.service.application.impl;

import com.ygsoft.user.management.dao.UserDao;
import com.ygsoft.user.management.domain.bo.UserBO;
import com.ygsoft.user.management.domain.po.UserPO;
import com.ygsoft.user.management.domain.transfer.UserTransfer;
import com.ygsoft.user.management.domain.vo.UserBatchVO;
import com.ygsoft.user.management.domain.vo.UserVO;
import com.ygsoft.user.management.infrastructure.constant.ErrorCode;
import com.ygsoft.user.management.infrastructure.constant.UserConstant;
import com.ygsoft.user.management.infrastructure.util.PasswordUtil;
import com.ygsoft.user.management.service.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 用户业务服务实现类
 * 
 * 实现用户相关的业务逻辑
 * 
 * @author developer
 * @date 2024-12-15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public String createUser(UserVO userVO) {
        // 1. 参数验证
        validateUserVO(userVO);
        
        // 2. 检查用户名是否已存在
        if (userDao.existsByUsername(userVO.getUsername())) {
            throw new IllegalArgumentException("用户名已存在");
        }
        
        // 3. VO 转 BO
        UserBO userBO = UserTransfer.voToBo(userVO);
        
        // 4. 生成用户ID
        String userId = generateUserId();
        userBO.setUserId(userId);
        
        // 5. 设置默认密码（加密）
        String encryptedPassword = PasswordUtil.encryptPassword(UserConstant.DEFAULT_PASSWORD);
        userBO.setPassword(encryptedPassword);
        
        // 6. 设置默认状态
        userBO.setStatus(UserConstant.STATUS_ENABLED);
        
        // 7. 设置创建时间
        userBO.setCreateTime(new Date());
        
        // 8. BO 转 PO
        UserPO userPO = UserTransfer.boToPo(userBO);
        
        // 9. 保存到数据库
        userDao.save(userPO);
        
        return userId;
    }
    
    @Override
    public boolean updateUser(UserVO userVO) {
        // 1. 参数验证
        if (userVO == null || userVO.getUserId() == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        
        // 2. 查询用户是否存在
        UserPO existingUser = userDao.findById(userVO.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("用户不存在"));
        
        // 3. 更新字段
        if (userVO.getRealName() != null) {
            existingUser.setRealName(userVO.getRealName());
        }
        if (userVO.getDepartment() != null) {
            existingUser.setDepartment(userVO.getDepartment());
        }
        if (userVO.getRole() != null) {
            existingUser.setRole(userVO.getRole());
        }
        if (userVO.getPhone() != null) {
            existingUser.setPhone(userVO.getPhone());
        }
        if (userVO.getEmail() != null) {
            existingUser.setEmail(userVO.getEmail());
        }
        
        // 4. 设置更新时间
        existingUser.setUpdateTime(new Date());
        
        // 5. 保存更新
        userDao.save(existingUser);
        
        return true;
    }
    
    @Override
    public int deleteUsers(UserBatchVO batchVO) {
        // 1. 参数验证
        validateBatchVO(batchVO);
        
        // 2. 批量删除
        List<String> userIds = batchVO.getUserIds();
        int count = userDao.deleteByUserIdIn(userIds);
        
        return count;
    }
    
    @Override
    public int resetPassword(UserBatchVO batchVO) {
        // 1. 参数验证
        validateBatchVO(batchVO);
        
        // 2. 查询用户列表
        List<String> userIds = batchVO.getUserIds();
        List<UserPO> users = userDao.findByUserIdIn(userIds);
        
        // 3. 重置密码
        String encryptedPassword = PasswordUtil.encryptPassword(UserConstant.DEFAULT_PASSWORD);
        int count = 0;
        
        for (UserPO user : users) {
            user.setPassword(encryptedPassword);
            user.setUpdateTime(new Date());
            userDao.save(user);
            count++;
        }
        
        return count;
    }
    
    @Override
    public int enableUsers(UserBatchVO batchVO) {
        return updateUserStatus(batchVO, UserConstant.STATUS_ENABLED);
    }
    
    @Override
    public int disableUsers(UserBatchVO batchVO) {
        return updateUserStatus(batchVO, UserConstant.STATUS_DISABLED);
    }
    
    /**
     * 更新用户状态
     * 
     * @param batchVO 批量操作对象
     * @param status 状态
     * @return 更新数量
     */
    private int updateUserStatus(UserBatchVO batchVO, String status) {
        // 1. 参数验证
        validateBatchVO(batchVO);
        
        // 2. 查询用户列表
        List<String> userIds = batchVO.getUserIds();
        List<UserPO> users = userDao.findByUserIdIn(userIds);
        
        // 3. 更新状态
        int count = 0;
        for (UserPO user : users) {
            user.setStatus(status);
            user.setUpdateTime(new Date());
            userDao.save(user);
            count++;
        }
        
        return count;
    }
    
    /**
     * 验证用户VO
     * 
     * @param userVO 用户VO
     */
    private void validateUserVO(UserVO userVO) {
        if (userVO == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        
        // 验证用户名
        if (userVO.getUsername() == null || userVO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        
        // 验证用户名长度
        if (userVO.getUsername().length() < UserConstant.USERNAME_MIN_LENGTH 
            || userVO.getUsername().length() > UserConstant.USERNAME_MAX_LENGTH) {
            throw new IllegalArgumentException(
                String.format("用户名长度必须在%d-%d个字符之间", 
                    UserConstant.USERNAME_MIN_LENGTH, 
                    UserConstant.USERNAME_MAX_LENGTH));
        }
        
        // 验证用户名格式
        if (!Pattern.matches(UserConstant.USERNAME_PATTERN, userVO.getUsername())) {
            throw new IllegalArgumentException("用户名只能包含字母、数字和下划线");
        }
        
        // 验证真实姓名
        if (userVO.getRealName() == null || userVO.getRealName().trim().isEmpty()) {
            throw new IllegalArgumentException("真实姓名不能为空");
        }
    }
    
    /**
     * 验证批量操作VO
     * 
     * @param batchVO 批量操作VO
     */
    private void validateBatchVO(UserBatchVO batchVO) {
        if (batchVO == null || batchVO.getUserIds() == null || batchVO.getUserIds().isEmpty()) {
            throw new IllegalArgumentException("用户ID列表不能为空");
        }
        
        if (batchVO.getUserIds().size() > UserConstant.MAX_BATCH_SIZE) {
            throw new IllegalArgumentException(
                String.format("批量操作数量不能超过%d", UserConstant.MAX_BATCH_SIZE));
        }
    }
    
    /**
     * 生成用户ID
     * 
     * @return 用户ID
     */
    private String generateUserId() {
        // 格式：U + 时间戳（13位） + 随机数（3位）
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000);
        return String.format("%s%d%03d", UserConstant.USER_ID_PREFIX, timestamp, random);
    }
}
