package com.example.demo.user.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User {
    @NotNull
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String userName;

    private String email;

    private String password;

    private String job;

    private int age;

    private boolean emailAcceptance;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus = UserStatus.USER_ACTIVE;

    public enum UserStatus {
        USER_ACTIVE("활동 상태"),
        USER_SLEEP("휴면 상태"),
        USER_QUIT("탈퇴 상태");

        @Getter
        private String status;

        UserStatus(String status) {
            this.status = status;
        }
    }

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

    private LocalDateTime deletedAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private List<String> roles = new ArrayList<>();

    public enum UserRole {
        ROLE_USER,
        ROLE_ADMIN;
    }
}
