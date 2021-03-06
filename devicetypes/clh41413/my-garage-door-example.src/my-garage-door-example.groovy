 
metadata {
	definition (name: "My Garage Door (example)", namespace: "clh41413", author: "Casey Hill") {
		capability "Contact Sensor"
        capability "Switch"
        command "poll"
	}

    simulator {
 
    }

	// Tile Definitions
	              
        standardTile("myContact1", "device.myContact1", width: 1, height: 1) {
			state("open", label:'${name}', icon:"st.contact.contact.open", backgroundColor:"#ffa81e")
			state("closed", label:'${name}', icon:"st.contact.contact.closed", backgroundColor:"#79b821")
		}
        
         standardTile("mySwitch1", "device.mySwitch1", width: 1, height: 1, canChangeIcon: true, canChangeBackground: true, defaultState: 0) {
			state "on", label: 'on', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
			state "off", label: 'off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
            
		}
       

		main(["mySwitch1"])
        details(["myContact1","mySwitch1","refresh"])
}

def poll(){
	myPoll()
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
    def msg = zigbee.parse(description)?.text
    log.debug "Parse got '${msg}'"

    def parts = msg.split(" ")
    def name  = parts.length>0?parts[0].trim():null
    //log.debug name + " is the name of the device"
    def value = parts.length>1?parts[1].trim():null
    //log.debug value + " is the value of the device"

   sendEvent(name: name, value: value, isStateChange: true)
   
}
//TODO: Need to define this on the device itself
def myPoll() {
    log.debug "Polling requested"
    zigbee.smartShield(text: "poll").format()
}

def on() {
    zigbee.smartShield(text: "SwitchOn").format()
}

/*
def off() {
	//sendEvent(name: "mySwitch1", value: "off")
	//zigbee.smartShield(text: "SwitchOn").format()
    //zigbee.smartShield(text: "zone2Off").format()
}*/