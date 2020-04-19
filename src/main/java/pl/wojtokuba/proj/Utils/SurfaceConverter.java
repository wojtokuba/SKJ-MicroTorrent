package pl.wojtokuba.proj.Utils;

public class SurfaceConverter {
    public static Float parseSurface(String surfaceString){
        String[] parsed = surfaceString.split(",");
        if(parsed.length == 3){
            return Float.parseFloat(parsed[0])*Float.parseFloat(parsed[1])*Float.parseFloat(parsed[2]);
        } else{
            return Float.parseFloat(surfaceString);
        }
    }
}
