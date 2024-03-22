package ru.itech.entity

import jakarta.persistence.*

@Entity
@Table(name = "community_table")
class CommunityEntity (
    @Id
        // добавляем эту аннотацию, тк поле id auto_increment
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Int = 0,
    var name: String  = "",
    var description: String = "",
    @OneToMany(mappedBy = "communityId")
        var members: List<UserEntity> = emptyList(),
)