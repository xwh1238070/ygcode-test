package com.ygsoft.user.management.infrastructure.util;

import java.security.SecureRandom;

/**
 * 密码工具类
 * 
 * 提供密码加密、验证等功能
 * 注意：实际项目中应使用BCrypt等成熟的加密算法
 * 
 * @author developer
 * @date 2024-12-15
 */
public class PasswordUtil {
    
    private static final SecureRandom RANDOM = new SecureRandom();
    
    /**
     * 加密密码
     * 
     * 注意：这里使用简单的示例实现
     * 实际项目中应使用BCrypt.hashpw()等安全的加密方法
     * 
     * @param plainPassword 明文密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            return null;
        }
        
        // 实际项目中应使用：
        // return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        
        // 这里仅作示例，实际应使用BCrypt
        return "encrypted_" + plainPassword + "_" + System.currentTimeMillis();
    }
    
    /**
     * 验证密码
     * 
     * @param plainPassword 明文密码
     * @param encryptedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verifyPassword(String plainPassword, String encryptedPassword) {
        if (plainPassword == null || encryptedPassword == null) {
            return false;
        }
        
        // 实际项目中应使用：
        // return BCrypt.checkpw(plainPassword, encryptedPassword);
        
        // 这里仅作示例
        return encryptedPassword.startsWith("encrypted_" + plainPassword);
    }
    
    /**
     * 生成随机密码
     * 
     * @param length 密码长度
     * @return 随机密码
     */
    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        
        return password.toString();
    }
}
