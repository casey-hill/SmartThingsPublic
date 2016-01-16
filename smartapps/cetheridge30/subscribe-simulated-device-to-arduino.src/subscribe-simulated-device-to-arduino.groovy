/**
 *
 *  Subscribe Simulated Device to Arduino
 *
 */
definition(
	name: "Subscribe Simulated Device to Arduino",
	namespace: "cetheridge30",
	author: "Christopher Etheridge",
	description: "Subscribes and Updates Simulated Device Based on Arduino PINs",
	category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png"

)

preferences {
	section("Select arduino that sensors are connected to.") {
		input "arduino", "capability.contactSensor", title: "Select...", required: true
    }
	section("Select the simulated sensor you would like to attach to Zone 1") {
        input "zone1", title: "Select...", "capability.contactSensor"
	}
    section("Select the simulated sensor you would like to attach to Zone 2") {
        input "zone2", title: "Select...", "capability.contactSensor"
	}
}

def installed()
{   
	subscribe()
    log.debug "Sending poll to update status on inital install"
    arduino.poll()
}

def updated()
{
	unsubscribe()
   	subscribe()
}

def subscribe()
{

		subscribe(arduino, "zone1.open", zone1OpenHandler)
        subscribe(arduino, "zone1.closed", zone1ClosedHandler)  
        subscribe(arduino, "zone2.open", zone2OpenHandler)
        subscribe(arduino, "zone2.closed", zone2ClosedHandler)   
 }

def zone1OpenHandler(evt) {
    //log.debug "arduinoevent($evt.name: $evt.value: $evt.deviceId)"
    zone1.open()
}

def zone1ClosedHandler(evt) {
    //log.debug "arduinoevent($evt.name: $evt.value: $evt.deviceId)"
    zone1.close()
}

def zone2OpenHandler(evt) {
    //log.debug "arduinoevent($evt.name: $evt.value: $evt.deviceId)"
    zone2.open()
}

def zone2ClosedHandler(evt) {
    //log.debug "arduinoevent($evt.name: $evt.value: $evt.deviceId)"
    zone2.close()
}

