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
	brackets = [[]]
	current_phase = 0
	# Track how long each bracket is, measured as the time the server is up and responding
	bracket_times = [0] 

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
						brackets.append([])
						bracket_times.append(0)

					brackets[current_phase].append(int(elems[4]))
					
				elif server_fail:
					if not is_crashed:
						is_crashed = True
						time_crashed = request_time
						bracket_times[current_phase] = time_crashed - bracket_start_time

	# Correct for if first line is a crash
	elems = lines[0].split(",")
	if elems[1] == "true" and len(recovery_times) > 0:
		del brackets[0]
		del recovery_times[0]

	# Only do the following analysis on data with faults injected
	# ie, don't do it for baselines
	if len(recovery_times) > 0:
		l = len(recovery_times)
		bracket_times[l] = end_time - bracket_start_time

		# Convert bracket times to seconds
		for i, val in enumerate(bracket_times):
			bracket_times[i] = val / 1000000000

		throughputs = [] # throughput = requests / sec
		response_times_ms = []
		for i in range(l + 1):
			throughputs.append(len(brackets[i]) / bracket_times[i])
			response_times_ms.append(sum(brackets[i]) / len(brackets[i]) / 1000000)

	# Calculate overall throughput and response time
	throughput = sum([len(i) for i in brackets]) / ((end_time - start_time) / 1000000000)
	throughput_without_failure = sum([len(i) for i in brackets]) / ((end_time - start_time - sum(recovery_times)) / 1000000000)
	avg_response_time = sum([sum(i) for i in brackets]) / sum(len(i) for i in brackets) / 1000000

	# Calculate recovery times and service availability
	# The baseline needs to be calculated differently than experiments with faults
	if len(recovery_times) > 0:
		avg_recovery_time_ms = sum(recovery_times) / len(recovery_times) / 1000000
		availability = (end_time - start_time - sum(recovery_times)) / (end_time - start_time)

		return throughputs, throughput, avg_recovery_time_ms, availability

	else:
		return [throughput]*5, throughput

if __name__ == "__main__":
    print(analyze_experiment(sys.argv[1]))