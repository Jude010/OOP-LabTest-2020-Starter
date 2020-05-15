package ie.tudublin;

import processing.data.TableRow;


public class Task {
    private String job ;
    private float start;
    private float end;

        public Task(TableRow row){

            job = row.getString("Task");
            start = row.getInt("Start");
            end = row.getInt("End");
        }    

        public String toString(){
            return
             job + "," +
             start + "," +
             end;

        }

        public String Job(){
            return job;
        }

        public float Start(){
            return start;
        }

        public float End(){
            return end;
        }

}