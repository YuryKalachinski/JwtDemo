package com.kalachinski.JWT.domain;

import lombok.*;
import net.bytebuddy.description.type.TypeList;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "usr")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"))
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
}


