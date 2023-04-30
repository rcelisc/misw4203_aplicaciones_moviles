package com.grupo15.vinilos.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CommentTest {

    @Test
    fun testCommentProperties() {
        val comment = Comment(
            1, "This is a comment", 4
        )

        assertEquals(1, comment.id)
        assertEquals("This is a comment", comment.description)
        assertEquals(4, comment.rating)
    }
}
