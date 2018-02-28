package com.manzoteam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private final String FILE_NAME = "me_at_the_zoo.in";

    private List<Integer> videoList;
    private List<Endpoint> endpointList;


    public void readFile() throws IOException {

        BufferedReader in = new BufferedReader(new FileReader(FILE_NAME));
        String line = "";
        String parameter[];

        //line one: video,endpoint,request,cache,size cache
        line = in.readLine();
        parameter = line.split(" ");

        //set number of videos
        // if(Integer.parseInt(parameter[0])<Video.LIMIT_NUMBER_VIDEOS){ }
        //Video.setNumberVideos(Integer.parseInt(parameter[0]));
        videoList = new ArrayList<>(Integer.parseInt(parameter[0]));

        //set number of endpoints
        // if(Integer.parseInt(parameter[1])<Endpoint.LIMIT_NUMBER_ENDPOINT){ }
        //Endpoint.setNumberEndpoint(Integer.parseInt(parameter[1]));
        endpointList = new ArrayList<>(Integer.parseInt(parameter[1]));

        int num_requests = Integer.parseInt(parameter[2]);


/*
        //set number of request
        if(Integer.parseInt(parameter[2])<Request.LIMIT_NUMBER_REQUEST_DESCRIPTION){
            Request.setNumberRequest(Integer.parseInt(parameter[2]));
        }
        //set number of caches
        if(Integer.parseInt(parameter[3])< Cache.LIMIT_NUMBER_CACHE_SERVER){
            Cache.setNumberCache(Integer.parseInt(parameter[3]));
        }
        //set number capacity
        if(Integer.parseInt(parameter[4])< Cache.LIMIT_CAPACITY_CACHE_SERVER){
            Cache.setNumberCapacityCache(Integer.parseInt(parameter[4]));
            for(int i = 0; i< Cache.getNumberCache(); i++){
                Cache.addCache(new Cache(i));
            }
        }
*/

        //line two: size of videos
        line = in.readLine();
        parameter = line.split(" ");
        Map<Integer, Integer> cacheServerList;
        for (int i = 0; i < Video.getNumberVideos(); i++)
            videoList.add(Integer.parseInt(parameter[i]));

        //read section endpoints
        int latency, connected_servs;
        for (int i = 0; i < endpointList.size(); i++) {

            //controllo su: latency video in datacenter, numbero di cache
            line = in.readLine();
            parameter = line.split(" ");
            latency = Integer.parseInt(parameter[0]);
            connected_servs = Integer.parseInt(parameter[1]);

            cacheServerList = new HashMap<>();
            for (int j = 0; j < connected_servs; j++) {
                line = in.readLine();
                String[] parameter_cache = line.split(" ");
                cacheServerList.put(Integer.parseInt(parameter_cache[0]), Integer.parseInt(parameter_cache[1]));
            }

            endpointList.add(new Endpoint(latency, cacheServerList));
        }


/*
        //read section request
        int endpointId;
        for (int i = 0; i < num_requests; i++) {

            line = in.readLine();
            String[] parameter_request = line.split(" ");

            endpointId = Integer.parseInt(parameter_request[1]);
            endpointList.

                    Endpoint.setRequestEndpoint(Endpoint.getEndpoint(Integer.parseInt(parameter_request[1])), Integer.parseInt(parameter_request[2]), Integer.parseInt(parameter_request[0]));

        }

        for request in range(num_requests):
        video_id, endpoint_id, n_requests = map( int,data.readline().strip().split())
        request_dict = {'video_id':video_id, 'endpoint_id':endpoint_id, 'n_requests':n_requests}
        requests.append(request_dict)
        endpoints[endpoint_id]['videos_req'].append(request_dict)
*/

        in.close();
    }
}
