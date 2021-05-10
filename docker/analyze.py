import numpy as np
import matplotlib.pyplot as plt
import sys

# Need to find throughput after each fault injection,
# recovery time from fault injection, and overall
# service availability.
#
# Additionally, for our experiments we will also compare 
# response times.
def analyze_experiment(file_path):
	
	# Time brackets to track throughput and respone time in each phase
	brackets = [[], [], [], [], []]
	current_phase = 0
	# Track how long each bracket is, measured as the time the server is up and responding
	bracket_times = [0] * 5

	# Time to recovery during each crash
	recovery_times = []

	with open(file_path, 'r') as file:
		lines = file.readlines()
		del lines[0]
		del lines[-1] # Delete last line because it could be incomplete

		# Start analysis 1 minute into experiment
		start_time = int(lines[0].split(",")[2]) + 1000000000 * 60
		# End analysis 1 minute from. end of experiment
		end_time = int(lines[-1].split(",")[2]) - 1000000000 * 60

		# Simple states used to track our current phase and recovery times
		is_crashed = False
		time_crashed = -1
		bracket_start_time = start_time

		for line in lines:
			elems = line.split(",")
			request_time = int(elems[2])
			# Ensure the current line exludes the first and last minutes of the experiment
			if request_time >= start_time and request_time <= end_time:
				server_fail = elems[1] == "true"

				if not server_fail:
					if is_crashed:
						is_crashed = False
						current_phase += 1
						# Don't calculate server time back up as request time --  a request
						# could have been sent before the server was back up, and just about reached
						# its timeout when the server returned. Use response time instead.
						recovery_times.append(int(elems[3]) - time_crashed)
						bracket_start_time = int(elems[3])

					brackets[current_phase].append(int(elems[4]))
					
				elif server_fail:
					if not is_crashed:
						is_crashed = True
						time_crashed = request_time
						bracket_times[current_phase] = time_crashed - bracket_start_time

	# Only do the following analysis on data with faults injected
	# ie, don't do it for baselines
	if len(recovery_times) == 4:
		bracket_times[4] = end_time - bracket_start_time

		# Convert bracket times to seconds
		for i, val in enumerate(bracket_times):
			bracket_times[i] = val / 1000000000

		throughputs = [] # throughput = requests / sec
		response_times_ms = []
		for i in range(5):
			throughputs.append(len(brackets[i]) / bracket_times[i])
			print("Throughput / sec for bracket " + str(i) + ": " + str(throughputs[i]))
			response_times_ms.append(sum(brackets[i]) / len(brackets[i]) / 1000000)
			print("Average response time (in milliseconds) for bracket " + str(i) + ": " + str(response_times_ms[i]))

	# Calculate overall throughput and response time
	print()
	throughput = sum([len(i) for i in brackets]) / ((end_time - start_time) / 1000000000)
	print("Overall throughout / sec: " + str(throughput))
	throughput_without_failure = sum([len(i) for i in brackets]) / ((end_time - start_time - sum(recovery_times)) / 1000000000)
	print("Throughout / sec excluding downtime: " + str(throughput_without_failure))
	avg_response_time = sum([sum(i) for i in brackets]) / sum(len(i) for i in brackets) / 1000000
	print("Average response time in milliseconds: " + str(avg_response_time))

	# Calculate recovery times and service availability
	print()
	# The baseline needs to be calculated differently than experiments with faults
	if len(recovery_times) > 0:
		avg_recovery_time_ms = sum(recovery_times) / len(recovery_times) / 1000000
		print("Average recovery ime in milliseconds: " + str(avg_recovery_time_ms))
		availability = (end_time - start_time - sum(recovery_times)) / (end_time - start_time)
		print("Availability: " + str(availability))
	else:
		print("Average recovery ime in milliseconds: --")
		print("Availability: 1.0")

analyze_experiment(sys.argv[1])