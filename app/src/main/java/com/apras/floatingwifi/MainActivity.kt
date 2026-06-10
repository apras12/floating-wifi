package com.apras.floatingwifi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
            private const val REQ_OVERLAY = 1001
                }

                    override fun onCreate(savedInstanceState: Bundle?) {
                            super.onCreate(savedInstanceState)

                                    if (!Settings.canDrawOverlays(this)) {
                                                Toast.makeText(this, "Izinkan tampil di atas aplikasi lain", Toast.LENGTH_LONG).show()
                                                            val intent = Intent(
                                                                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                                                                            Uri.parse("package:$packageName")
                                                                                                        )
                                                                                                                    startActivityForResult(intent, REQ_OVERLAY)
                                                                                                                            } else {
                                                                                                                                        startFloatingService()
                                                                                                                                                }
                                                                                                                                                    }

                                                                                                                                                        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                                                                                                                                                                super.onActivityResult(requestCode, resultCode, data)
                                                                                                                                                                        if (requestCode == REQ_OVERLAY) {
                                                                                                                                                                                    if (Settings.canDrawOverlays(this)) {
                                                                                                                                                                                                    startFloatingService()
                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                Toast.makeText(this, "Permission ditolak. App tidak bisa jalan.", Toast.LENGTH_LONG).show()
                                                                                                                                                                                                                                                finish()
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                        }

                                                                                                                                                                                                                                                                            private fun startFloatingService() {
                                                                                                                                                                                                                                                                                    val serviceIntent = Intent(this, FloatingService::class.java)
                                                                                                                                                                                                                                                                                            startForegroundService(serviceIntent)
                                                                                                                                                                                                                                                                                                    moveTaskToBack(true)
                                                                                                                                                                                                                                                                                                            finish()
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                }