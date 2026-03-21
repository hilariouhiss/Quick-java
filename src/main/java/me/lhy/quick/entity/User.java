package me.lhy.quick.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.lhy.quick.validation.constraint.ConsistentSoftDeleteAudit;
import me.lhy.quick.validation.group.CreateGroup;
import me.lhy.quick.validation.group.UpdateGroup;

/**
 * 系统用户表
 */
@TableName(value ="users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "passwordHash")
@ConsistentSoftDeleteAudit
public class User implements Serializable {

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增ID
     */
    @TableId(type = IdType.AUTO)
    @Null(groups = CreateGroup.class, message = "新增时自增id必须为空")
    @NotNull(groups = UpdateGroup.class, message = "更新时id不能为空")
    @Positive(message = "id必须为正数")
    private Long id;

    /**
     * 用户名，逻辑未删除范围内唯一
     */
    @NotBlank(message = "用户名不能为空")
    @Size(max = 64, message = "用户名长度不能超过64")
    private String username;

    /**
     * 邮箱，逻辑未删除范围内唯一
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;

    /**
     * 使用人
     */
    @NotNull(message = "使用人不能为空")
    @Size(max = 64, message = "使用人长度不能超过64")
    private String usedBy;

    /**
     * 工号
     */
    @NotNull(message = "工号不能为空")
    @Size(max = 64, message = "工号长度不能超过64")
    private String employeeNo;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Size(max = 32, message = "手机号长度不能超过32")
    @Pattern(regexp = "^[0-9+\\-()\\s]*$", message = "手机号格式不正确")
    private String phone;

    /**
     * 密码哈希
     */
    @NotBlank(message = "密码哈希不能为空")
    @Size(min = 60, max = 60, message = "密码哈希长度必须为60")
    private String passwordHash;

    /**
     * 账号状态：DISABLED、ACTIVE、LOCKED
     */
    @NotNull(message = "账号状态不能为空")
    private UserStatus status;

    /**
     * 创建人ID
     */
    @NotNull(message = "创建人ID不能为空")
    @Positive(message = "创建人ID必须为正数")
    private Long createdBy;

    /**
     * 更新人ID
     */
    @NotNull(message = "更新人ID不能为空")
    @Positive(message = "更新人ID必须为正数")
    private Long updatedBy;

    /**
     * 删除人ID
     */
    @Positive(message = "删除人ID必须为正数")
    private Long deletedBy;

    /**
     * 乐观锁版本号
     */
    @NotNull(message = "乐观锁版本号不能为空")
    @Positive(message = "乐观锁版本号必须为正数")
    private Integer version;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空")
    @PastOrPresent(message = "创建时间不能晚于当前时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @NotNull(message = "更新时间不能为空")
    @PastOrPresent(message = "更新时间不能晚于当前时间")
    private Date updatedAt;

    /**
     * 逻辑删除时间，NULL 表示未删除
     */
    @PastOrPresent(message = "删除时间不能晚于当前时间")
    private Date deletedAt;
}
