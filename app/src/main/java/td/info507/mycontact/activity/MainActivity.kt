package td.info507.mycontact.activity

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import td.info507.mycontact.model.Contact
import td.info507.mycontact.R

class MainActivity : AppCompatActivity() {

    private val CALL_PHONE_CODE : Int = 1
    private val SEND_SMS_CODE : Int = 1;
    private val IMAGE_CAPTURE_CODE : Int = 1;

    private lateinit var  contact : Contact


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contact= intent.getSerializableExtra(ListActivity.EXTRA_CONTACT) as Contact

        findViewById<TextView>(R.id.account_name).text = contact.name;
        findViewById<TextView>(R.id.concact_phone).text = contact.phone;
        findViewById<TextView>(R.id.contact_other_phone).text = contact.home;
        findViewById<TextView>(R.id.contact_location).text = contact.location;
        findViewById<TextView>(R.id.contact_mail).text = contact.mail;

        val llCall : LinearLayout = findViewById(R.id.linear_layout_call)
        val llSms : LinearLayout = findViewById(R.id.linear_layout_sms)
        val llMail : LinearLayout = findViewById(R.id.linear_layout_mail)

        llCall.setOnClickListener {
            if (checkPermission(permission.CALL_PHONE, CALL_PHONE_CODE)) {
                callPhone()
            }
        }

        llSms.setOnClickListener {
            if(checkPermission(permission.SEND_SMS, SEND_SMS_CODE)) {
                sendMessage()
            }
        }

        llMail.setOnClickListener {
            sendMail()
        }

        findViewById<ImageView>(R.id.account_icon).setOnClickListener{
            takePhoto()
        }


    }

    private fun callPhone(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${contact.phone}"))
        startActivity(intent)
    }

    private fun sendMessage(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${contact.phone}"))
        startActivity(intent)
    }

    private fun sendMail(){
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${contact.mail}"))
        startActivity(intent)
    }

    private fun takePhoto(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
    }

    private fun checkPermission(permission: String, requestCode: Int): Boolean {
        var res = true
        if (ContextCompat.checkSelfPermission(this, permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
            }
            res = false
        }
        return res
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PHONE_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                callPhone()
            }
        }
    }


}

