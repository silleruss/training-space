package com.silleruss.central.core.converters

import org.springframework.core.io.ClassPathResource
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO

class ImageConverter {

    fun convertToWatermarkedJpgFile(source: InputStream): File {
        return source.use {
            ImageIO.setUseCache(false)

            val image = ImageIO.read(source)
            val bufferedImage = removeAlphaChannelIfExists(image)
            val result = drawWatermark(bufferedImage)

            writeImageToFile(result)
        }
    }

    private fun removeAlphaChannelIfExists(image: BufferedImage): BufferedImage {
        return if (image.type in alphaTypeSet) removeAlphaChannel(image) else image
    }

    private fun removeAlphaChannel(image: BufferedImage): BufferedImage {
        return BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB).apply {
            createGraphics().drawImage(image, 0, 0, Color.WHITE, null)
        }
    }

    private fun drawWatermark(image: BufferedImage): BufferedImage {
        val graphics2D = setAlphaComposeFromGraphics(image)

        return try {
            val xs = 0..image.width step watermarkImage.width
            val ys = 0..image.height step watermarkImage.height

            val coordinates = xs.flatMap { x -> ys.map { y -> x to y } }

            coordinates.forEach { (x, y) ->
                graphics2D.drawImage(watermarkImage, x, y, null)
            }

            image
        } finally {
            graphics2D.dispose()
        }
    }

    private fun setAlphaComposeFromGraphics(sourceImage: BufferedImage): Graphics2D {
        return (sourceImage.graphics as Graphics2D).apply {
            composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, WATERMARK_ALPHA_VALUE)
        }
    }

    private fun writeImageToFile(image: BufferedImage): File {
        val filename = UUID.randomUUID()
        val fullFilename = "$filename.$JPEG_OUTPUT_FILE_FORMAT"
        val dirPathname = tempDirPath.toAbsolutePath().toString()

        return File(dirPathname, fullFilename).apply {
            ImageIO.write(image, JPEG_OUTPUT_FILE_FORMAT, this)
        }
    }

    companion object {

        private const val WATERMARK_FILE_NAME = "watermark.jpg"
        private const val WATERMARK_ALPHA_VALUE = 0.1f

        private const val JPEG_OUTPUT_FILE_FORMAT = "jpg"

        private val watermarkResource = ClassPathResource(WATERMARK_FILE_NAME)

        val tempDirPath: Path = Paths.get(System.getProperty("java.io.tmpdir"))
        val watermarkImage: BufferedImage = ImageIO.read(watermarkResource.file)

        val alphaTypeSet = setOf(
            BufferedImage.TYPE_INT_ARGB,
            BufferedImage.TYPE_4BYTE_ABGR,
            BufferedImage.TYPE_4BYTE_ABGR_PRE
        )

    }

}