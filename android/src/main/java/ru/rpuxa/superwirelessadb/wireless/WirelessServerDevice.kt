package ru.rpuxa.superwirelessadb.wireless

import ru.rpuxa.internalserver.wifi.WifiDevice
import ru.rpuxa.internalserver.wireless.*

class WirelessServerDevice(device: WifiDevice) : WirelessDevice(device) {

    init {
        wifiDevice.stream.onMessage { command, data ->
            val ans: Any? = when (command) {
                GET_DEVICE_PASSPORT -> AndroidPassport
                else -> throw IllegalStateException("Unknown command")
            }

            ans
        }
    }

    override fun checkAdbConnection(): WirelessPromise<Boolean> =
            sendMessage(CHECK_ADB)

    override fun connectAdb(): WirelessPromise<Int> =
            sendMessage(CONNECT_ADB)

    override fun getDevicePassport(): WirelessPromise<Passport> =
            sendMessage(GET_DEVICE_PASSPORT)


}