package com.example.smsservice

import android.telephony.SmsManager
import fi.iki.elonen.NanoHTTPD
import org.json.JSONObject
import java.io.IOException

class SmsServer : NanoHTTPD("0.0.0.0",8080) {
    init {
        start(SOCKET_READ_TIMEOUT, false)
        println("Server started on port 8080")
    }

    override fun serve(session: IHTTPSession?): Response {
        if (session?.method == Method.POST) {
            val map = HashMap<String, String>()
            session.parseBody(map)
            val params = session.parameters

            val number = params["number"]?.get(0) // Assure-toi que le paramètre 'number' est présent
            val message = params["message"]?.get(0) // Assure-toi que le paramètre 'message' est présent

            if (number != null && message != null) {
                try {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(number, null, message, null, null)
                    val responseMessage=createJsonResponse(true,"SMS envoyé avec succès à $number")
                    return newFixedLengthResponse(responseMessage)
                } catch (e: Exception) {
                    e.printStackTrace()
                    val responseMessage = createJsonResponse(false, "Échec de l'envoi du SMS")
                    return newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "application/json", responseMessage)
                }
            } else {
                val responseMessage = createJsonResponse(false, "Numéro ou message manquant")
                return newFixedLengthResponse(Response.Status.INTERNAL_ERROR,"application/json", "Numéro ou message manquant")
            }
        }

        return newFixedLengthResponse("Hello from NanoHTTPD!")
    }

    fun createJsonResponse(statusCode: Boolean, message: String): String {
        val jsonResponse = JSONObject()
        jsonResponse.put("status_code", statusCode)
        jsonResponse.put("message", message)
        return jsonResponse.toString()
    }
}
