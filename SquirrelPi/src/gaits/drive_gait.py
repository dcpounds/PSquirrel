

class DriveGait():
	"""
	gait for driving up and down the tree
	"""
	
	def __init__(self, direction, sensor_manager, drive_motor_manager):
		"""
		initialize gait
		
		direction - direction of gait (UP or DOWN)
		sensor_manager - manager of the sensors
		"""
		self.direction = direction
		self.sensor_manager = sensor_manager
		self.drive_motor_manager = drive_motor_manager
		
	def execute(self):
		"""
		execute the next step of the gait
		"""	
		self.drive_motor_manager.maintainDistance()
		if(self.sensor_manager.areClawsAttached("BOTTOM")):
			self.bottom_cycle()
		elif(self.sensor_manger.areClawsAttached("TOP")):
			self.top_cycle()
		else:
			self.middle_cycle()
					
	def bottom_cycle(self):
		"""
		cycle of the gait dealing with moving the bottom half
		"""
		if(self.direction == "UP"):
			if(self.sensor_manager.isLeadScrewRetracted()):
				self.drive_motor_manager.attachClaws("BOTTOM")
			else:
				self.drive_motor_manager.driveLeadScrew("RETRACT")
		elif(self.direction == "DOWN"):
			if(self.sensor_manager.isLeadScrewExtended()):
				self.drive_motor_manager.attachClaws("BOTTOM")
			else:
				self.drive_motor_manager.driveLeadScrew("EXTEND")
					
	def top_cycle(self):
		"""
		cycle of the gait dealing with moving the top half
		"""
		if(self.direction == "UP"):
			if(self.sensor_manager.isLeadScrewExtended()):
				self.drive_motor_manager.attachClaws("TOP")
			else:
				self.drive_motor_manager.driveLeadScrew("EXTEND")
		elif(self.direction == "DOWN"):
			if(self.sensor_manager.isLeadScrewRetracted()):
				self.drive_motor_manager.attachClaws("TOP")
			else:
				self.drive_motor_manager.driveLeadScrew("RETRACT")
					
	def middle_cycle(self):
		"""
		cycle of the gait dealing with releasing claws
		"""
		if(self.direction == "UP"):
			if(self.sensor_manager.isLeadScrewExtended()):
				self.drive_motor_manager.detachClaws("BOTTOM")
			else:
				self.drive_motor_manager.detachClaws("TOP")
		elif(self.direction == "DOWN"):
			if(self.sensor_manager.isLeadScrewRetracted()):
				self.drive_motor_manager.detachClaws("BOTTOM")
			else:
				self.drive_motor_manager.detachClaws("TOP")
		
					
	
		
		
					
					