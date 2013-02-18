from gridmap import *
from distribution import *
import copy
import random
import itertools
from collections import deque
import copy

################
## Useful global values
################

# Actions an agent can perform -- three directional motions, 
# two turns, or remain still
actionNames = ['stay','f','fl','fr','l','r']
# Directions an agent can be facing
directions = ['e','ne','n','nw','w','sw','s','se']
# Map of direction values to their indices in the list
directionMap = zip(directions,range(len(directions)))
# Dict providing direction -> vector mappings to corresponding x,y vectors
directionVectors = {'e':(1,0),'ne':(1,1),'n':(0,1),'nw':(-1,1),'w':(-1,0),'sw':(-1,-1),'s':(0,-1),'se':(1,-1)}
directionVectorMap = dict((v,k) for (k,v) in directionVectors.iteritems())
# If I turn left while facing X, I will be facing Y
directionLeft = {'e':'ne','ne':'n','n':'nw','nw':'w','w':'sw','sw':'s','s':'se','se':'e'}
# Same but reversed
directionRight = dict((v,k) for (k,v) in directionLeft.iteritems())

def next_pose(pose,action):
	"""A deterministic action model for a single agent, mapping a
	pose,action pair to next pose.  Poses are tuples (x,y,d)."""
	if action == 'f':
		d = directionVectors[pose[2]]
		candidate = (pose[0]+d[0],pose[1]+d[1],pose[2])
	elif action == 'fl':
		return next_pose(map,next_pose(map,pose,'l'),'f')
	elif action == 'fr':
		return next_pose(map,next_pose(map,pose,'r'),'f')
	elif action == 'l':
		return (pose[0],pose[1],directionLeft[pose[2]])
	elif action == 'r':
		return (pose[0],pose[1],directionRight[pose[2]])
	return pose

def next_pose(map,pose,action):
	"""A deterministic action model for a single agent, mapping a
	pose,action pair to next pose.  Poses are tuples (x,y,d)."""
	if action == 'f':
		d = directionVectors[pose[2]]
		candidate = (pose[0]+d[0],pose[1]+d[1])
		if map == None or valid_cell(map,candidate):
			return (candidate[0],candidate[1],pose[2])
	elif action == 'fl':
		return next_pose(map,next_pose(map,pose,'l'),'f')
	elif action == 'fr':
		return next_pose(map,next_pose(map,pose,'r'),'f')
	elif action == 'l':
		return (pose[0],pose[1],directionLeft[pose[2]])
	elif action == 'r':
		return (pose[0],pose[1],directionRight[pose[2]])
	return pose

def next_pose_with_objects(map,pose,action,objects):
	"""The deterministic action model, taking into account that
	the agent might hit another oject"""
	next = next_pose(map,pose,action)
	if (next[0],next[1]) in [(o[0],o[1]) for o in objects.itervalues()]:
		#stay still
		return pose
	return next

def valid_actions(map,pose):
	"""Returns a list of actions from actionNames which may be
	taken in the given pose on the given map -- does not take other
	objects into account, just obstacles"""
	res = []
	for a in actionNames:
		if next_pose(None,pose,a) == next_pose(map,pose,a):
			res.append(a)
	return res

def distance2(a,b):
        """Returns L2-distance"""
        return math.sqrt(pow(a[0]-b[0],2) + pow(a[1]-b[1],2))

class AgentState:
	"""A class representing the true pose, goal, and other-object poses (?)
	of an agent"""
	def __init__(self,mypose=None,mygoal=None,objectPoses={}):
		self.mypose = mypose
		self.mygoal = mygoal
		self.objectPoses = objectPoses

def position_visible(pose,position,maxrange):
	"""Returns 0 if the given x,y position out of the agent's
	viewing angle and maxrange at the given pose.  Otherwise,
	returns 1 for left half of view, 2 for right half"""
	l = directionVectors[directionLeft[pose[2]]]
	r = directionVectors[directionRight[pose[2]]]
	d = (position[0]-pose[0],position[1]-pose[1])
	dp_left = l[0]*d[0] + l[1]*d[1]
	dp_right = r[0]*d[0] + r[1]*d[1]
	d_norm2 = d[0]*d[0]+d[1]*d[1]
	if dp_left >= 0 and dp_right > 0 and d_norm2 <= maxrange*maxrange:
                if dp_left >= dp_right:
                        return 'L'
                return 'R'
        return 0


class GoalSensorModel:
	"""Models the probability that an agent senses a goal"""
	def __init__(self,maxrange=100,p_error=0.0):
		"""Sets the maximum range and uncertainty of the sensor"""
		self.maxrange = maxrange
		self.p_error = p_error

	def probability_raw(self,pose,goal,value):
		"""Given a pose and goal, returns the probability that the
		agent correctly reports seeing or not seeing the goal from
		that pose."""
		if position_visible(pose,goal,self.maxrange) != value:
			return self.p_error
		return 1.0-self.p_error

	def probability(self,state,value):
		"""Same as probability_raw() but with an AgentState
		for pose and goal"""
		if position_visible(state.mypose,state.mygoal,self.maxrange) != value:
			return self.p_error
		return 1.0-self.p_error

	def sample(self,state):
		"""Given an AgentState with goal and pose, returns correct goal sensing
		with probability p_error and incorrect goal sensing with probability
		1 - p_error."""
		trueval = position_visible(state.mypose,state.mygoal,self.maxrange)
		if random.random() < self.p_error:
			return not trueval
		return trueval

class ObjectSensorModel:
	"""Same as GoalSensorModel but for other objects.  Proximity and a view
        sensor."""
	def __init__(self,name,proximity=2,p_view_err=0.05,p_prox_fp=0.1,p_prox_fn=0.1):
		self.name = name
		self.proximity = proximity
		self.view_dist = {0:{0:1.0-p_view_err,'L':p_view_err*0.5,'R':p_view_err*0.5},'L':{'L':1.0-p_view_err,0:p_view_err*0.5,'R':p_view_err*0.5},'R':{'R':1.0-p_view_err,'L':p_view_err*0.5,0:p_view_err*0.5}}
		self.prox_dist = {True:{True:1.0-p_prox_fn,False:p_prox_fn},False:{True:p_prox_fp,False:1.0-p_prox_fp}}		

	def probability_raw(self,pose,opose,value):
                trueprox = (distance2(opose,pose) <= self.proximity)
                trueview = position_visible(pose,(opose[0],opose[1]),100)
		return self.view_dist[trueview][value[0]]*self.prox_dist[trueprox][value[1]]
		
	def probability(self,state,value):
                return self.probability_raw(state.mypose,state.objectPoses[self.name])

	def sample(self,state):
                pose = state.mypose            
                opose = state.objectPoses[self.name]
                trueprox = distance2(opose,pose) <= self.proximity
		trueview = position_visible(pose,(opose[0],opose[1]),100)
		view = sample_distribution(self.view_dist[trueview])
                prox = sample_distribution(self.prox_dist[trueprox])
		return (view,prox)

class AgentObservation:
	"""Records a pose, whether a goal is visible in the pose, and
	object->visible mappings for all other objects."""
	def __init__(self,mypose,goalvisible=False,objectvisible={}):
		self.mypose = mypose
		self.goalvisible = goalvisible
		self.objectvisible = objectvisible

class AgentSensorModel:
	"""Combines GoalSensorModel and ObjectSensorModel to report
	on the likelihood of AgentObservations."""
	def __init__(self,goalSensor,objectSensors={}):
		self.goalSensor = goalSensor
		self.objectSensors = objectSensors
	
	def probability(self,state,observation):
		"""Given an AgentState and AgentObservation, returns the likelihood
		of that observation in that state."""
		if state.mypose != observation.mypose: return 0.0
		Pviewed = 1.0
		Pviewed *= self.goalSensor.probability(state,observation.goalvisible)
		for obj,vis in observation.objectvisible:
			Pviewed *= self.objectSensors[obj].probability(state,vis)
		return Pviewed
	
	def sample(self,state):
		"""Samples an AgentObservation randomly using given AgentState"""
		obs = AgentObservation(state.mypose)
		obs.goalvisible = self.goalSensor.sample(state)
		obs.objectvisible = dict((name,val.sample(state)) for (name,val) in self.objectSensors.iteritems())
		return obs


class SingleAgentBehaviorModel:
	"""By default, a simple state-independent markovian behavior model.
	The distribution is specified as a dict mapping action names to action
	probabilities.  It can also vary by pose"""
	def __init__(self,map,p_action = 'uniform'):
                self.map = map
		# the self.p_action is the dict mapping actions to probs
		if p_action == 'uniform':
			self.p_action = uniform_distribution(actionNames)
		else:
			self.p_action = p_action

	def action_distribution(self,pose):
                actions = valid_actions(self.map,pose)
		dist = {}
		for a in actions:
                        dist[a] = self.p_action[a]
                normalize(dist)
                return dist
                        

	def next_state_distribution(self,pose):
		dist = dict()
		for a,p in self.action_distribution(pose).iteritems():
			ns = next_pose(self.map,pose,a)
			dist[ns] = dist.get(ns,0.0) + p
		return dist


class AgentBeliefState:
	"""Provides current pose, cell->prob mappings for goal beliefs,
	object->[(cell,direction)->prob] mappings for beliefs about object
	locations and directional headings"""
	def __init__(self,pose):
		self.mypose = pose
		self.b_goal = None
		self.b_objects = None

	def init_uniform_goals(self,map):
		self.b_goal = uniform_distribution(enumerate_map_cells(map))
		
	def init_uniform_objects(self,map,names):
		self.b_objects = {}
		for oname in names:
			self.b_objects[oname] = uniform_distribution( \
						cartesian_product(iterate_map_cells(map),directions))

	def sample_true_state(self):
		"""Returns a sample of the true underlying state, based on
		current beliefs"""
		s = AgentState()
		s.mypose = self.mypose
		if self.b_goal:
			s.mygoal = sample_distribution(self.b_goal)
		if self.b_objects:
			s.objectposes = dict((name,sample_distribution(bo)) \
								for (name,bo) in self.b_objects.iteritems())
		return s

class AgentPolicyBase:
	"""A policy defining actions given current beliefs and time"""
	def __init__(self,map):
		self.map = map

	def evaluate(self,beliefState):
		"""Subclasses should override this to return an action."""
		pass

	def advance(self):
		"""Indicates a timestep has elapsed.  Subclasses may override this."""
		pass

class AgentOpenLoopPolicy(AgentPolicyBase):
	"""A fixed path policy"""
	
	def __init__(self,map,actionlist,repeat=False):
		AgentPolicyBase.__init__(self,map)
		self.actionlist = actionlist
		self.progression = 0
		self.repeat = repeat

	def evaluate(self,beliefState):
		if self.progression < len(self.actionlist):
			return self.actionlist[self.progression]
		return actionNames[0]
	
	def advance(self):
		self.progression += 1
		if self.repeat and self.progression >= len(self.actionlist):
                        self.progression=0
                        
        def done(self):
                return self.progression >= len(self.actionlist)

class Agent:
	def __init__(self,name,map):
		self.name = name
		self.map = map
		self.policy = None
		self.currentBelief = None
		self.sensorModel = None
		self.agentBehaviorModels = None

	def set_policy(self,policy):
		self.policy = policy

	def set_sensor_model(self,sensorModel):
		self.sensorModel = sensorModel

	def set_behaviors(self,numagents,behavior_model):
		self.agentBehaviorModels = [behavior_model]*numagents
	
	def update_belief(self,b,observation):
		bnext = AgentBeliefState(observation.mypose)
		#update goal belief
		bnext.b_goal = self.update_goal_belief(bnext.mypose,b.b_goal,observation.goalvisible)
		#update object beliefs
		bnext.b_objects = dict()
		for (o,bo) in b.b_objects.iteritems():
			bnext.b_objects[o] = self.update_object_belief(bnext.mypose,o,bo,observation.objectvisible[o])
		return bnext

	def update_goal_belief(self,mypose,bgoal,goalvisible):
		"""Given the new pose of the agent and the belief bgoal over goal
		positions from the prior time step, return
		an updated distribution on goals given the observation goalvisible
		"""
		#TODO (Question 1a): fill this in -- you may want to use
		#    self.sensorModel.goalSensor.probability_raw
		#print mypose, bgoal.keys(), goalvisible
		for i in bgoal:
			tempProbability = 0.0
			tempProbability += self.sensorModel.goalSensor.probability_raw(mypose, i, goalvisible)
			if (bgoal[i] != 0.0):
				bgoal[i] = tempProbability
			else:
				continue
		return bgoal.copy()

	def update_object_belief(self,mypose,objname,bobject,obsvisible):
		"""Given the new pose of the agent and the predicted belief
                bobject on object pose (obtained via a previous call to
                predict_object_belief), return an updated distribution
		on the object pose, given the observation obsvisible.
		"""
		#TODO (Question 1c): fill this in -- you may want to use
		#    self.sensorModel.objectSensors[objname].probability_raw and
		#for i in bobject:
		#	bobject[i] *= self.sensorModel.objectSensors[objname].probability_raw(mypose, i, obsvisible)
		#normalize(bobject)
		for i in bobject:
			tempProbability = 0.0
			tempProbability += self.sensorModel.objectSensors[objname].probability_raw(mypose, i, obsvisible)
			if bobject[i] != 0.0:
				bobject[i] *= tempProbability
			else:
				continue
		normalize(bobject)
		return bobject.copy()

        def predict_belief(self,b,action):
                bnext = AgentBeliefState(next_pose(self.map,b.mypose,action))
		#update goal belief
		bnext.b_goal = b.b_goal.copy()
		#update object beliefs
		bnext.b_objects = dict()
		for (o,bo) in b.b_objects.iteritems():
			bnext.b_objects[o] = self.predict_object_belief(bnext.mypose,o,bo)
		#print bnext.b_objects
		return bnext

        def predict_object_belief(self,mypose,objname,bobject):
			"""Given the chosen action of the agent and the belief bobject
			of object positions on the prior step, return an updated
			distribution on object positions.
			"""
			#TODO (Question 1b): fill this in -- you may want to use
			#    self.agentBehaviorModels[objname]
			tempProbabilityDistribution = {}
			#tempProbabilityDistribution =  self.agentBehaviorModels[objname].next_state_distribution(mypose)
			#print self.agentBehaviorModels[objname].action_distribution(mypose)
			#print tempProbabilityDistribution
			tempDist = bobject.copy()
			for i in bobject:
				tempProbabilityDistribution =  self.agentBehaviorModels[objname].next_state_distribution(i)
				for j in tempProbabilityDistribution:
					#if i != j:
					tempDist[j] += (tempProbabilityDistribution[j] * bobject[i])
				#bobject[i] = tempDist[j]
			#print bobject
			return tempDist

        def most_likely_trajectory(self,mypath,objname,objpercepts):
                """Given a path of my own states mypath, estimate the most
                likely object trajectory for object objname that generated the
                given percept list.  The initial distribution of object states
                can be assumed to be uniform."""
                #TODO (Question 2): fill in the missing lines
                #print objpercepts
                assert(len(mypath)==len(objpercepts))
                states = cartesian_product(iterate_map_cells(self.map),directions)
                obsprobs0 = dict()
                for s in states:
                        obsprobs0[s] = self.sensorModel.objectSensors[objname].probability_raw(mypath[0],s,objpercepts[0])
                #calculate maximized probabilities of states on time t in the forward pass (denoted V[i,k] in the slides)
                V = [obsprobs0]
                #print V
                #store the previous state that contributed to the V probability at time t (denoted pi[i,k] in the slides)
                parents = [dict((s,None) for s in states)]
                for (mypose,objvisible) in zip(mypath,objpercepts)[1:]:
                        #do the maximization of the forward viterbi probability, store the
                        #resulting probability in p.  Store the state that gave rise to
                        #the parent value in best.  

                        Vlast = V[-1]
                        p = dict()
                        best = dict((s,None) for s in states)
                        transition = dict((s,0.0) for s in states)
                        
                        # TODO: Replace these lines with your code to account for transition
                        #probabilities.  You will need to incorporate
                        #  self.agentBehaviorModels[objname].next_state_distribution()
                        for x in Vlast.keys():
                        	next_states = self.agentBehaviorModels[objname].next_state_distribution(x)
                        	for z in next_states:
                        		if(transition[z] < Vlast[x] * next_states[z]):
                        			transition[z] = Vlast[x] * next_states[z]
                        			#transition[z] = x
                        
                        for s in states:
                                #this line picks the previous state that maximizes the observation probability
                                temp = self.agentBehaviorModels[objname].next_state_distribution(s)
                                #print temp
                                best[s] = max((v,k) for (k,v) in Vlast.iteritems())[1]
                                #this line computes the probability of the current observation
                                obsprob = self.sensorModel.objectSensors[objname].probability_raw(mypose,s,objvisible)
                                #this line computes the the probability of the observations for the most
                                #likely state sequence, not considering transition probabilities
                                p[s] = obsprob*transition[s]
                                
                        # Add p and best to the list
                        V.append(p)
                        parents.append(best)

                #perform the backwards step of the Viterbi algorithm
                sequence = [None]*len(V)
                sequence[-1] = max((v,k) for (k,v) in V[-1].iteritems())[1]
                for i in reversed(range(1,len(V))):
                        sequence[i-1] = parents[i][sequence[i]]
                return sequence

        def sense(self,obs,rewards):
		"""Observation/reward update."""
		bnext = self.update_belief(self.currentBelief,obs)
		self.currentBelief = bnext
	
	def act(self):
		"""Return the chosen action and update internal state if necessary."""
		a = self.policy.evaluate(self.currentBelief)
		self.policy.advance()
		bnext = self.predict_belief(self.currentBelief,a)
		self.currentBelief = bnext
		return a
		
