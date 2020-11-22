WGRAPH_DS
	void	addNode​(int key)	
	add node to the graph
	
	void	connect​(int node1, int node2, double w)	
	connect 2 nodes

	int	edgeSize()	
	returning the amount of edges

	double	getEdge​(int node1, int node2)	
	get edge between 2 nodes

	int	getMC()	
	returning the mc

	node_info	getNode​(int key)	
	returning node

	java.util.Collection<node_info>	getV()	
	returning all the nodes from graph

	java.util.Collection<node_info>	getV​(int node_id)	
	returning copy of neighbors collection

	boolean	hasEdge​(int node1, int node2)	
	checking if 2 nodes have edge

	int	nodeSize()	
	returning the amount of nodes

	void	removeEdge​(int node1, int node2)	
	remove edge from graph

	node_info	removeNode​(int key)	
	remove node from graph

WGRAPH_ALGO
	weighted_graph	copy()	
	making a deep copy of the graph

	weighted_graph	getGraph()	
	Return the underlying graph of which this class works.

	void	init​(weighted_graph g)	
	Init the graph on which this set of algorithms operates on.

	boolean	isConnected()	
	checking if all the nodes connected to each other

	boolean	load​(java.lang.String file)	
	loading the graph

	boolean	save​(java.lang.String file)	
	saving the graph

	java.util.List<node_info>	shortestPath​(int src, int dest)	
	returning the shortest list of nodes from src to dest

	double	shortestPathDist​(int src, int dest)	
	returning the shortest distance in number from src to dest






HashMap
void	clear()
Removes all of the mappings from this map.

Object	clone()
Returns a shallow copy of this HashMap instance: the keys and values themselves are not 
cloned.

V	compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)
Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).

V	computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction)
If the specified key is not already associated with a value (or is mapped to null), attempts to compute its value using the given mapping function and enters it into this map unless null.

V	computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction)
If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.

boolean	containsKey(Object key)
Returns true if this map contains a mapping for the specified key.

boolean	containsValue(Object value)
Returns true if this map maps one or more keys to the specified value.

Set<Map.Entry<K,V>>	entrySet()
Returns a Set view of the mappings contained in this map.

void	forEach(BiConsumer<? super K,? super V> action)
Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.

V	get(Object key)
Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.

V	getOrDefault(Object key, V defaultValue)
Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.

boolean	isEmpty()
Returns true if this map contains no key-value mappings.

Set<K>	keySet()
Returns a Set view of the keys contained in this map.

V	merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction)
If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.

V	put(K key, V value)
Associates the specified value with the specified key in this map.

void	putAll(Map<? extends K,? extends V> m)
Copies all of the mappings from the specified map to this map.

V	putIfAbsent(K key, V value)
If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.

V	remove(Object key)
Removes the mapping for the specified key from this map if present.

boolean	remove(Object key, Object value)
Removes the entry for the specified key only if it is currently mapped to the specified value.

V	replace(K key, V value)
Replaces the entry for the specified key only if it is currently mapped to some value.

boolean	replace(K key, V oldValue, V newValue)
Replaces the entry for the specified key only if currently mapped to the specified value.

void	replaceAll(BiFunction<? super K,? super V,? extends V> function)
Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.

int	size()
Returns the number of key-value mappings in this map.

Collection<V>	values()
Returns a Collection view of the values contained in this map.








Queue

boolean	add(E e)
Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.

E	element()
Retrieves, but does not remove, the head of this queue.

boolean	offer(E e)
Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.

E	peek()
Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.

E	poll()
Retrieves and removes the head of this queue, or returns null if this queue is 		empty.
E	remove()
Retrieves and removes the head of this queue.

