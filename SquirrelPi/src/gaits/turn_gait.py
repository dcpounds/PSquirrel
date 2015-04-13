

class TurnGait():
	"""
	gait for turning left and right
	"""
	
	def __init__(self, direction, sensor_manager, drive_motor_manager):
		"""
		initialize gait
		
		direction - direction of turn (LEFT or ROGHT)
		sensor_manager - manager of the sensors
		"""
		self.direction = direction
		self.sensor_manager = sensor_manager
		self.drive_motor_manager = drive_motor_manager
		
	def execute(self):
		"""
		execute the next step of the gait
		"""
		if(not self.sensor_manager.areClawsAttached("BOTTOM")):
			self.drive_motor_manager.attachClaws("BOTTOM")
		elif(self.sensor_manger.areClawsAttached("TOP")):
			self.drive_motor_manager.detachClaws("TOP")
		else:
			self.drive_motor_manager.turnStep("YAW", self.direction)
					
					