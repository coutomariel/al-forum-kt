package br.com.alura.forum.api.service

import br.com.alura.forum.domain.Course
import org.springframework.stereotype.Service

@Service
class CourseService(
    var courses: List<Course>
) {

    init {
        val course = Course(
            id = 1,
            name = "Kotlin cabuloso",
            category = "Programação"
        )
        val courseOther = Course(
            id = 2,
            name = "Java ninja",
            category = "Programação"
        )

        courses = listOf(course, courseOther)
    }

    fun getById(id: Long): Course? {
        return courses.find { course -> course.id == id }
    }
}

