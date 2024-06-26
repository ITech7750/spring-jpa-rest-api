package ru.itech.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_table")
class UserEntity (
    @Id
    // добавляем эту аннотацию, тк поле id auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int = 0,

    var name: String  = "",

    var wish: String  = "",

    @ManyToOne
    @JoinColumn(name = "community_id")
    var communityId: CommunityEntity?,

    var dependId: Int?,
    )