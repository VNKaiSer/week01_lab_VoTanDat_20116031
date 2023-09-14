package vn.edu.iuh.fit.lab_week01.constant;

public interface UIClass {
    static String alerSuccess(String message){
        return "<div class=\"bg-blue-100 border-t border-b border-green-500 text-blue-700 px-4 py-3\" role=\"alert\">\n" +
                "  <p class=\"font-bold\">Informational message</p>\n" +
                "  <p class=\"text-sm\">"+message+"</p>\n" +
                "</div>";
    }

    static String alerWarning(String message){
        return "<div class=\"bg-orange-100 border-l-4 border-orange-500 text-orange-700 p-4\" role=\"alert\">\n" +
                "  <p class=\"font-bold\">Be Warned</p>\n" +
                "  <p>"+message+"</p>\n" +
                "</div>";
    }

    static  String alerDanger(String message){
        return "<div class=\"bg-red-100 border-l-4 border-red-500 text-red-700 p-4\" role=\"alert\">\n" +
                "  <p class=\"font-bold\">Danger</p>\n" +
                "  <p>"+message+"</p>\n" +
                "</div>";
    }


}
