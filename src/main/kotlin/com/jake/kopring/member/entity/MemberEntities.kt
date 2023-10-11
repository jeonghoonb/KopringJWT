package com.jake.kopring.member.entity

import com.jake.kopring.common.status.Gender
import com.jake.kopring.common.status.ROLE
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.AUTO
import jakarta.persistence.TemporalType.DATE
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_id", columnNames = ["memberId"])]
)
class Member(
    @Id
    @GeneratedValue(strategy = AUTO)
    var no: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val memberId: String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false)
    @Temporal(DATE)
    val birthDate: LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(STRING)
    val gender: Gender,

    @Column(nullable = false, length = 30)
    val email: String
) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberRole: List<MemberRole>? = null
}

@Entity
class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var no: Long? = null,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val role: ROLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_user_role_member_id"))
    val member: Member
)