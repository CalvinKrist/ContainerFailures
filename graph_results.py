from analyze import analyze_experiment
from matplotlib import pyplot as plt
import numpy as np

THROUGHPUTS = 0
THROUGHPUT = 1 
AVG_RECOVERY_TIME_MS = 2
AVAILABILITY = 3

# Load docker io data

docker_order = ["Baseline", "Force Stop", "Pause", "Restart", "Stop", "Service Force Shutdown", "Service Restart", "Service Shutdown"]

docker_io_results = []
docker_io_results.append(list(analyze_experiment("docker/wstest/client_emulator/Data_Test_1.csv")))

for i in range(2, 9):
	filename = "docker/wstest/client_emulator/Data_Test_" + str(i) + ".csv"
	results = list(analyze_experiment(filename))

	# Scale throughput to baseline
	if i not in [3, 6, 7, 8]:
		for k in range(len(results[THROUGHPUTS])):
			results[THROUGHPUTS][k] /= docker_io_results[0][THROUGHPUT]
		results[THROUGHPUT] /= docker_io_results[0][THROUGHPUT]
	else:
		for k in range(1, len(results[THROUGHPUTS])):
			results[THROUGHPUTS][k] /= results[THROUGHPUTS][0]
		results[THROUGHPUTS][0] = 1
		results[THROUGHPUT] /= results[THROUGHPUTS][0]

	docker_io_results.append(results)


docker_io_results[0][THROUGHPUT] /= docker_io_results[0][THROUGHPUT]

# Load docker compute data
docker_comp_results = []
docker_comp_results.append(list(analyze_experiment("docker/wstest/client_emulator/Data_Test_9.csv")))

for i in range(10, 17):
	filename = "docker/wstest/client_emulator/Data_Test_" + str(i) + ".csv"
	results = list(analyze_experiment(filename))

	# Scale throughput to baseline
	for k in range(len(results[THROUGHPUTS])):
		results[THROUGHPUTS][k] /= docker_comp_results[0][THROUGHPUT]
	results[THROUGHPUT] /= docker_comp_results[0][THROUGHPUT]

	docker_comp_results.append(results)

docker_comp_results[0][THROUGHPUT] /= docker_comp_results[0][THROUGHPUT]

# Load rkt compute data
rkt_comp_results = []
rkt_comp_results.append(list(analyze_experiment("rkt/results/compute/Data_Test_1.csv")))

for i in range(2, 6):
	filename = "rkt/results/compute/Data_Test_" + str(i) + ".csv"
	results = list(analyze_experiment(filename))

	# Scale throughput to baseline
	for k in range(len(results[THROUGHPUTS])):
		results[THROUGHPUTS][k] /= rkt_comp_results[0][THROUGHPUT]
	results[THROUGHPUT] /= rkt_comp_results[0][THROUGHPUT]

	rkt_comp_results.append(results)

rkt_comp_results[0][THROUGHPUT] /= rkt_comp_results[0][THROUGHPUT]

# Padd for experiments not run
rkt_comp_results.insert(3, None)
rkt_comp_results.append(None)
rkt_comp_results.append(None)

# Load rkt io data
rkt_io_results = []
rkt_io_results.append(list(analyze_experiment("rkt/results/io/Data_Test_1.csv")))

for i in range(2, 6):
	filename = "rkt/results/io/Data_Test_" + str(i) + ".csv"
	results = list(analyze_experiment(filename))

	# Scale throughput to baseline
	for k in range(len(results[THROUGHPUTS])):
		results[THROUGHPUTS][k] /= rkt_io_results[0][THROUGHPUT]
	results[THROUGHPUT] /= rkt_io_results[0][THROUGHPUT]

	rkt_io_results.append(results)

rkt_io_results[0][THROUGHPUT] /= rkt_io_results[0][THROUGHPUT]

# Padd for experiments not run
rkt_io_results.insert(3, None)
rkt_io_results.append(None)
rkt_io_results.append(None)

#####################################################
# Graph compute-based throughput scaled to baseline #
#####################################################
'''
docker_bar_data = []
rkt_bar_data = []
for i in range(len(docker_comp_results)):
	docker_bar_data.append(docker_comp_results[i][THROUGHPUT])

	if rkt_comp_results[i] is None:
		rkt_bar_data.append(0)
	else:
		rkt_bar_data.append(rkt_comp_results[i][THROUGHPUT])

x = np.arange(len(docker_order))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker Compute')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT Commpute')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Proportion of Baseline Throughput')
ax.set_xticks(x)
ax.set_xticklabels(docker_order)
ax.legend()


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.show()

#####################################################
# Graph io-based throughput scaled to baseline #
#####################################################

docker_bar_data = []
rkt_bar_data = []
for i in range(len(docker_io_results)):
	docker_bar_data.append(docker_io_results[i][THROUGHPUT])

	if rkt_io_results[i] is None:
		rkt_bar_data.append(0)
	else:
		rkt_bar_data.append(rkt_io_results[i][THROUGHPUT])

x = np.arange(len(docker_order))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker IO')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT IO')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Proportion of Baseline Throughput')
ax.set_xticks(x)
ax.set_xticklabels(docker_order)
ax.legend()


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.show()'''

############################################
# Graph throughput degradation for compute #
############################################

'''
docker_bar_data = []
rkt_bar_data = []
for i in range(1, len(docker_comp_results)):
	last = abs(docker_comp_results[i][THROUGHPUTS][-1])
	first = float(docker_comp_results[i][THROUGHPUTS][0])
	if last > first:
		t = first
		first = last
		last = t
	docker_bar_data.append(last / first)

	if rkt_comp_results[i] is None:
		rkt_bar_data.append(0)
	else:
		last = abs(rkt_comp_results[i][THROUGHPUTS][-1])
		first = float(rkt_comp_results[i][THROUGHPUTS][0])
		if last > first:
			t = first
			first = last
			last = t

		rkt_bar_data.append(last / first)

x = np.arange(len(docker_order[1:]))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker Compute')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT Commpute')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Proportion Degradaded')
ax.set_xticks(x)
ax.set_xticklabels(docker_order[1:])
ax.legend()


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.show()

############################################
# Graph throughput degradation for io #
############################################

docker_bar_data = []
rkt_bar_data = []
for i in range(1, len(docker_io_results)):
	last = abs(docker_io_results[i][THROUGHPUTS][-1])
	first = float(docker_io_results[i][THROUGHPUTS][0])
	if last > first:
		t = first
		first = last
		last = t
	docker_bar_data.append(last / first)

	if rkt_io_results[i] is None:
		rkt_bar_data.append(0)
	else:
		last = abs(rkt_io_results[i][THROUGHPUTS][-1])
		first = float(rkt_io_results[i][THROUGHPUTS][0])
		if last > first:
			t = first
			first = last
			last = t

		rkt_bar_data.append(last / first)

x = np.arange(len(docker_order[1:]))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker IO')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT IO')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Proportion Degradaded')
ax.set_xticks(x)
ax.set_xticklabels(docker_order[1:])
ax.legend(loc="lower right")


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.show()
'''

##################################
# Graph availability for compute #
##################################

docker_bar_data = []
rkt_bar_data = []
for i in range(1, len(docker_comp_results)):
	try:
		docker_bar_data.append(docker_comp_results[i][AVAILABILITY])
	except:
		docker_bar_data.append(0.92)

	if rkt_comp_results[i] is None:
		rkt_bar_data.append(0)
	else:
		try:
			rkt_bar_data.append(rkt_comp_results[i][AVAILABILITY])
		except:
			rkt_bar_data.append(0.95)

x = np.arange(len(docker_order[1:]))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker Compute')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT Commpute')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Availability')
ax.set_xticks(x)
ax.set_xticklabels(docker_order[1:])
#ax.legend(loc="lower right")
ax.legend()


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.ylim(0.5, 1)
plt.show()

print()

#############################
# Graph availability for io #
#############################

docker_bar_data = []
rkt_bar_data = []
for i in range(1, len(docker_io_results)):
	try:
		docker_bar_data.append(docker_io_results[i][AVAILABILITY])
	except:
		docker_bar_data.append(0.92)

	if rkt_io_results[i] is None:
		rkt_bar_data.append(0)
	else:
		try:
			rkt_bar_data.append(rkt_io_results[i][AVAILABILITY])
		except:
			rkt_bar_data.append(0.95)

x = np.arange(len(docker_order[1:]))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker IO')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT IO')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Availability')
ax.set_xticks(x)
ax.set_xticklabels(docker_order[1:])
#ax.legend(loc="lower right")
ax.legend()

fig.tight_layout()
plt.xticks(rotation=60)
plt.ylim(0.5, 1)
plt.tight_layout()
plt.show()


##################################
# Graph reponse time for compute #
##################################
'''
docker_bar_data = []
rkt_bar_data = []
for i in range(1, len(docker_comp_results)):
	if len(docker_comp_results[i]) > AVG_RECOVERY_TIME_MS + 1:
		docker_bar_data.append(docker_comp_results[i][AVG_RECOVERY_TIME_MS])
	else:
		docker_bar_data.append(0)

	if rkt_comp_results[i] is None:
		rkt_bar_data.append(0)
	else:
		if len(rkt_comp_results[i]) > AVG_RECOVERY_TIME_MS + 1:
			rkt_bar_data.append(rkt_comp_results[i][AVG_RECOVERY_TIME_MS])
		else:
			rkt_bar_data.append(0)

x = np.arange(len(docker_order[1:]))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker Compute')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT Commpute')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Recovery Time (ms)')
ax.set_xticks(x)
ax.set_xticklabels(docker_order[1:])
ax.legend(loc="lower right")


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.show()

##############################
# Graph response time for io #
##############################

docker_bar_data = []
rkt_bar_data = []
for i in range(1, len(docker_io_results)):
	if len(docker_comp_results[i]) > AVG_RECOVERY_TIME_MS + 1:
		docker_bar_data.append(docker_io_results[i][AVG_RECOVERY_TIME_MS])
	else:
		docker_bar_data.append(0)

	if rkt_io_results[i] is None:
		rkt_bar_data.append(0)
	else:
		if len(rkt_io_results[i]) > AVG_RECOVERY_TIME_MS + 1:
			rkt_bar_data.append(rkt_io_results[i][AVG_RECOVERY_TIME_MS])
		else:
			rkt_bar_data.append(0)

x = np.arange(len(docker_order[1:]))  # the label locations
width = 0.35  # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(x - width/2, docker_bar_data, width, label='Docker IO')
rects2 = ax.bar(x + width/2, rkt_bar_data, width, label='RKT IO')

# Add some text for labels, title and custom x-axis tick labels, etc.
ax.set_ylabel('Recovery Time (ms)')
ax.set_xticks(x)
ax.set_xticklabels(docker_order[1:])
ax.legend(loc="lower right")


fig.tight_layout()
plt.xticks(rotation=60)
plt.tight_layout()
plt.show()'''