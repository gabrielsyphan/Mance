package com.syphan.store.controller.api.product

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Paths

@Controller
@RequestMapping("/api/v1/store")
class GetProductImageApiController {

    @GetMapping("/products/image/{filename:.+}")
    fun getProductImage(@PathVariable filename: String): ResponseEntity<Resource> {
        val filePath = Paths.get("uploads").resolve(filename).normalize()
        val resource: Resource
        try {
            resource = UrlResource(filePath.toUri())
            if (!resource.exists()) {
                return ResponseEntity.notFound().build()
            }
        } catch (e: MalformedURLException) {
            return ResponseEntity.badRequest().build()
        }

        // Tenta descobrir o content type do arquivo
        val contentType = try {
            Files.probeContentType(filePath)
        } catch (ex: IOException) {
            "application/octet-stream"
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType ?: "application/octet-stream"))
            .body(resource)
    }
}