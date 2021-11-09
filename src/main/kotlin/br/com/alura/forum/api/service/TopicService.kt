package br.com.alura.forum.api.service

import br.com.alura.forum.api.controller.CreateTopicRequest
import br.com.alura.forum.api.controller.TopicResponse
import br.com.alura.forum.api.controller.UpdateTopicRequest
import br.com.alura.forum.api.exception.NotFoundException
import br.com.alura.forum.domain.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    var topics: List<Topic> = ArrayList(),
    val userService: UserService,
    val courseService: CourseService
) {
    companion object {
        var nextId: Long = 1
    }

    fun save(request: CreateTopicRequest): TopicResponse {
        val course = courseService.getById(request.idCourse)
            ?: throw NotFoundException("Course related in this topic not found")
        val author = userService.getById(request.idAuthor)
            ?: throw NotFoundException("User related in this topic not found")

        val topicToSave: Topic = request.toModel(course = course, author = author)
        topics = topics.plus(topicToSave)

        return topicToSave.toDto()
    }

    fun getAllTopics(): List<TopicResponse> {
        return topics.map { topic -> topic.toDto() }
    }

    fun getTopicById(id: Long): TopicResponse {
        return getTopic(id).toDto()
    }

    fun update(id: Long, request: UpdateTopicRequest): TopicResponse {
        val oldTopic = getTopic(id)
        val updatedTopic = request.toModel(oldTopic)
        topics = topics.minus(oldTopic).plus(updatedTopic)
        return updatedTopic.toDto()
    }

    fun delete(id: Long) {
        getTopicById(id)
        topics = topics.filter { topic -> topic.id != id }
    }

    private fun getTopic(id: Long): Topic {
        return topics.find { topic -> topic.id == id }
            ?: throw NotFoundException("Topic with this ID not found")
    }

}

private fun Topic.toDto(): TopicResponse {
    return TopicResponse(
        id = this.id!!,
        title = this.title,
        message = this.message,
        createdAt = this.createdAt,
        status = this.status
    )
}