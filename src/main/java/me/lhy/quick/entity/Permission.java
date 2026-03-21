package me.lhy.quick.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.lhy.quick.validation.constraint.ConsistentSoftDeleteAudit;
import me.lhy.quick.validation.group.CreateGroup;
import me.lhy.quick.validation.group.UpdateGroup;

/**
 * 权限点表
 */
@TableName(value = "permissions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@ConsistentSoftDeleteAudit
public class Permission implements Serializable {

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
     * 资源标识，例如 user、project
     */
    @NotBlank(message = "资源标识不能为空")
    @Size(max = 100, message = "资源标识长度不能超过100")
    private String resource;

    /**
     * 动作标识，例如 read、write、delete
     */
    @NotBlank(message = "动作标识不能为空")
    @Size(max = 50, message = "动作标识长度不能超过50")
    private String action;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    @Size(max = 128, message = "权限名称长度不能超过128")
    private String name;

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
