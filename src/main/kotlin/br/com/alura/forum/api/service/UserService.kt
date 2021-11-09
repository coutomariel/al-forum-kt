package br.com.alura.forum.api.service

import br.com.alura.forum.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(
    var users: List<User> = ArrayList()
) {

    init {
        val joao = User(
            id = 1,
            name = "Joao",
            email = "joao@gmail.com"
        )
        val pedro = User(
            id = 2,
            name = "Pedro",
            email = "pedro@gmail.com"
        )

        users = listOf(joao, pedro)
    }

    fun getById(id: Long): User? {
        return users.find { user -> user.id == id }
    }
}
