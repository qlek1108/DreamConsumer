package com.example.demo.user;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "users")
@Table(name = "USER_TB")
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
    @Column(name = "USER_STATUS")
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
}
