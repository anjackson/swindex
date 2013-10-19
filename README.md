swindex
=======

Software Index

Idea is to use Solr to make searchable indexes of software from various sources.

Starting with NIST NSRL
* Take the RDS and index it
* Add http://web.archive.org/web/20130525234644/http://www.nsrl.nist.gov/morealgs.htm including ssdeep ngrams
* Add http://web.archive.org/web/20130526023312/http://www.nsrl.nist.gov/Diskprints.htm
* May include other data, http://web.archive.org/web/20130929225803/http://www.nsrl.nist.gov/

Also use tools to index software held at IA, mergeing the other information source in to allow cross-references, and to track software over time, across sources, and to build signatures.
* http://archive.org/details/On_Hand_From_Softbank_1994_Release_2_Disc_2_1994
* http://md5deep.sourceforge.net/

Related to and complementing NSRL Query
* http://rjhansen.github.io/nsrllookup/



Running the Solr instance
-------------------------

If you run

  mvn jetty:run

This will fire up a Solr instance, using the config from ./src/main/solr, storing the data in ./target, visible at:

  http://localhost:8080/


Errors

2013-10-19 22:09:14 ERROR SolrCore:119 - null:java.lang.NoClassDefFoundError: org/apache/commons/logging/LogFactory
    at org.apache.http.impl.client.AbstractHttpClient.<init>(AbstractHttpClient.java:187)
    at org.apache.http.impl.client.DefaultHttpClient.<init>(DefaultHttpClient.java:127)
    at org.apache.http.impl.client.SystemDefaultHttpClient.<init>(SystemDefaultHttpClient.java:113)
    at org.apache.solr.client.solrj.impl.HttpClientUtil.createClient(HttpClientUtil.java:104)
