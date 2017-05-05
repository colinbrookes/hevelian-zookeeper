# hevelian-zookeeper

Enterprise class software for the rest of us.

Run Apache ZooKeeper as a WAR deployment in Tomcat and reduce the number of processes you need to start up.

To run:
modify Tomcat context.xml to include one Environment parameter "hevelian.zookeeper.home", for example:

&lt;Environment name="hevelian.zookeeper.home" value="/hevelian/zookeeper" type="java.lang.String"/&gt;

In the home folder create three folders, "conf", "log" and "data". 
In "conf" folder create a file "hevelian.properties" and add the following to the file:

client.port=2181<br/>
snap.directory=/hevelian/zookeeper/data<br/>
log.directory=/hevelian/zookeeper/log<br/>
tick.time=3000<br/>
max.clients=100<br/>

Change the snap.directory and log.directory to point to the filders you created of course :)
