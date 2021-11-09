package br.com.alura.forum.api.controller

import br.com.alura.forum.api.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(
    private val topicService: TopicService
) {
    @PostMapping
    fun create(@RequestBody @Valid request: CreateTopicRequest, builder: UriComponentsBuilder): ResponseEntity<Any> {
        val topicSaved = topicService.save(request)
        val uriBuilder: URI = builder.path("/topics/{id}").buildAndExpand(topicSaved.id).toUri()
        return ResponseEntity.created(uriBuilder).body(topicSaved.id)
    }

    @GetMapping
    fun list(): List<TopicResponse> {
        return topicService.getAllTopics()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): TopicResponse {
        return topicService.getTopicById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        topicService.delete(id)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid request: UpdateTopicRequest): TopicResponse {
        return topicService.update(id, request)
    }
}

