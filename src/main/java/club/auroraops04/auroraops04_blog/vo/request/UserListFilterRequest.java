package club.auroraops04.auroraops04_blog.vo.request;

import club.auroraops04.auroraops04_blog.entities.User;

import java.util.List;

public class UserListFilterRequest extends User {
    private List<Long> roleIds;
    private List<Long> permissionIds;
}
