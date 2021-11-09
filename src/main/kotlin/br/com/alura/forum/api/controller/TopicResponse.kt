package br.com.alura.forum.api.controller

import br.com.alura.forum.domain.StatusTopic
import java.time.LocalDateTime

class TopicResponse(
    val id: Long,
    val title: String,
    val message: String,
    val createdAt: LocalDateTime,
    val status: StatusTopic
)