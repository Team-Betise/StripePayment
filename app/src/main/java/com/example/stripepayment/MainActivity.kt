package com.example.stripepayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.stripe.android.PaymentConfiguration
import com.stripe.android.googlepaylauncher.GooglePayEnvironment
import com.stripe.android.googlepaylauncher.GooglePayLauncher

class MainActivity : AppCompatActivity() {
    private lateinit var googlePayButton: View
    private val PUBLISHABLE_KEY = "pk_test_51MNJZRSBabmINvRGeDgooRKD2m9roviM0miifBA8UUJc1kIUUnZglEEk9FvyRRzHHKZcdLA2GvtHD4IWjmpls4x8001LBNeuFw"
    private val clientSecret = "sk_test_51MNJZRSBabmINvRGOy2MWjepqCoovaoNaJoKzuWy6lmSuYsDMHh2CvMZTP1IlzsqpBvXSIFLTUzO2v9Dmg5gnKHs00VkLUYMgv"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PaymentConfiguration.init(this, PUBLISHABLE_KEY)

        googlePayButton = findViewById(R.id.button)
        println("hello")
        val googlePayLauncher = GooglePayLauncher(
            activity = this,
            config = GooglePayLauncher.Config(
                environment = GooglePayEnvironment.Test,
                merchantCountryCode = "IN",
                merchantName = "Betise"
            ),
            readyCallback = ::onGooglePayReady,
            resultCallback = ::onGooglePayResult
        )
        println("Hello 2")
        googlePayButton.setOnClickListener {
        println("Button clicked")
            // launch `GooglePayLauncher` to confirm a Payment Intent
            googlePayLauncher.presentForPaymentIntent(clientSecret)
        }

    }

    private fun onGooglePayReady(isReady: Boolean) {
        googlePayButton.isEnabled = isReady
    }

    private fun onGooglePayResult(result: GooglePayLauncher.Result) {
        when (result) {
            GooglePayLauncher.Result.Completed -> {
                // Payment succeeded, show a receipt view
                println("Payment succeeded!!!! Zynade")
            }
            GooglePayLauncher.Result.Canceled -> {
                // User canceled the operation
                println("User canceled the operation!!! Zynade")
            }
            is GooglePayLauncher.Result.Failed -> {
                // Operation failed; inspect `result.error` for the exception
                println("Operation failed; inspect `result.error` for the exception!!! Zynade")
            }
        }
    }
}