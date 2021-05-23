package com.kueblearn.user_service.service;

import com.kueblearn.user_service.domain.Role;
import com.kueblearn.user_service.domain.User;
import com.kueblearn.user_service.model.UserDTO;
import com.kueblearn.user_service.repos.RoleRepository;
import com.kueblearn.user_service.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLoginName(user.getLoginName());
        userDTO.setUserRoless(user.getUserRolesRoles() == null ? null : user.getUserRolesRoles().stream()
                .map((role) -> role.getId())
                .collect(Collectors.toList()));
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setLoginName(userDTO.getLoginName());
        if (userDTO.getUserRoless() != null) {
            final List<Role> userRoless = roleRepository.findAllById(userDTO.getUserRoless());
            if (userRoless.size() != userDTO.getUserRoless().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of userRoless not found");
            }
            user.setUserRolesRoles(userRoless.stream().collect(Collectors.toSet()));
        }
        return user;
    }

}
