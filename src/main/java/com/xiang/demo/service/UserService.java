package com.xiang.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiang.common.Result;
import com.xiang.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XR
 * @since 2021-01-07
 */
public interface UserService extends IService<User> {

    Result login(User user, HttpServletResponse response);

    Result registerOrUpdateUserById(User user);

    Result getUserById(Long id);

    Result deleteUserById(Long id);

    Result deleteUsersByBatchId(Collection<Long> batchIds);

    Result getAllUserAndPages(Page page,String searchField);

    Result assignRoles(Long userId, Long roleId);

    Result validationSend(String email);

    Result forgotPasswordByVerificationCode(User user);

    Result updatePassword(Map<String, Object> data);

    Result avatarUpload(MultipartFile file, Long id);

    Result updateNickName(User user);

    Result getAllManAndPages(Page page, String searchField);

    Result getAllUsersAndPages(Page page, String searchField);
}
