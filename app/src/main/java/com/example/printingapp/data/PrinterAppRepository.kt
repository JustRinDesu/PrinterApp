package com.example.printingapp.data

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import com.example.printingapp.model.Order
import com.example.printingapp.model.User
import com.example.printingapp.network.PrinterApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File

interface PrinterAppRepository {
    suspend fun getAllOrders(): List<Order>
    suspend fun getOrderById(id: String): Order
    suspend fun createOrder(newOrder: Order): Response<Void>
    suspend fun updateOrder(id: String, updatedOrder: Order): Response<Void>
    suspend fun deleteOrder(id: String): Response<Void>

    suspend fun loginUser(user: User): Response<User>

    suspend fun uploadFile(uri: Uri,context: Context): FileResponse
}

@Serializable
data class FileResponse(
    var fileId : String
)

class NetworkPrinterAppRepository(
    private val printerApiServices: PrinterApiService


) : PrinterAppRepository {
    override suspend fun getAllOrders(): List<Order> = printerApiServices.getAllOrders()
    override suspend fun getOrderById(id: String): Order = printerApiServices.getOrderById(id)
    override suspend fun createOrder(newOrder: Order): Response<Void> =
        printerApiServices.createOrder(newOrder)
    override suspend fun updateOrder(id: String, updatedOrder: Order): Response<Void> =
        printerApiServices.updateOrder(id, updatedOrder)
    override suspend fun deleteOrder(id: String): Response<Void> =
        printerApiServices.deleteOrder(id)

    override suspend fun loginUser(user: User): Response<User> {
        return printerApiServices.loginUser(user)
    }

    override suspend fun uploadFile(uri: Uri,context: Context): FileResponse {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(uri)

        // Create a temporary file from the input stream (for upload purposes)
        val tempFile = withContext(Dispatchers.IO) {
            // Extract the file extension from the Uri
            val fileExtension = context.contentResolver.getType(uri)?.let {
                MimeTypeMap.getSingleton().getExtensionFromMimeType(it)
            } ?: "tmp"  // Default to "tmp" if the MIME type isn't found

            // Create the temp file with the extracted extension
            File.createTempFile("upload_temp", ".$fileExtension", context.cacheDir)
        }.apply {
            // Open input stream and copy the contents from Uri
            inputStream?.let { inputStream ->
                outputStream().use { fileOut ->
                    inputStream.copyTo(fileOut)
                }
            }
        }

// Create request body for the file
        val requestBody = tempFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("file", tempFile.name, requestBody)

        return printerApiServices.uploadFile(part)
    }

}
