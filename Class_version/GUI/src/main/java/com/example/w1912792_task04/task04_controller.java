package com.example.w1912792_task04;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Queue;

public class task04_controller {

    @FXML
    private TextArea ViewFuelQueue;
    @FXML
    private TextArea ViewWaitingQueue;
    @FXML
    private TextField search_input;
    @FXML
    private TextArea search_results;
    @FXML
    private TextArea FuelPumpIncome;
    @FXML
    private TextField TotalFuelIncome;

    //Scenes switching
    @FXML
    public void switchToHome(ActionEvent event) throws IOException {
        //identify the previous stage and close it
        Parent root=FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Stage currentWindow=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root,600,400);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }
    public void switchToViewQueues(ActionEvent event)throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("ViewAllQueues.fxml"));
        Stage currentWindow=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root,600,400);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }
    public void switchToSearch(ActionEvent event)throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("SearchPassengers.fxml"));
        Stage currentWindow=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root,600,400);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }
    public void switchToFuelIncome(ActionEvent event)throws IOException{
        Parent root=FXMLLoader.load(getClass().getResource("FuelIncome.fxml"));
        Stage currentWindow=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root,600,400);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }

    @FXML
    //Displaying Fuel Queue
    protected void FuelViewBtn_onClick() {
        FuelQueue Queue[][] = FuelQueue.getFuelQueue();
        String FuelQueueDetails="";
        for (int row=0;row<Queue.length;row++){
            FuelQueueDetails+="Queue No: "+(row+1)+"\n";
            for (int col=0;col<Queue[row].length;col++){
                FuelQueueDetails+=(col+1)+") Queue no, "+(row+1)+" location "+(col+1)+":\n";
                if (Queue[row][col]!=null){
                    FuelQueueDetails+=("\tFirst Name: "+Queue[row][col].getFirstName()+"\n\tSecond Name: "+Queue[row][col].getSecondName()+"\n\tVehicle No: "+Queue[row][col].getVehicleNo()+"\n\tLiters requied: "+Queue[row][col].getLitersRequired()+"\n");
                }else {
                    FuelQueueDetails+=("\tEmpty space\n");
                }
            }FuelQueueDetails+="\n";
        }
        ViewFuelQueue.setText(FuelQueueDetails);
    }

    @FXML
    //display waiting queue
    protected void WaitingViewBtn_onClick(){
        String WaitingQueueDetails="";
        int count=1;
        FuelQueue waitingQueue[]=WaitingList.getWaiting_list();
        System.out.println(waitingQueue);
        if (WaitingList.isEmpty()){//check waiting queue is empty
            WaitingQueueDetails+="\n\tWaiting Queue is empty";
        }else {
            WaitingQueueDetails+=WaitingList.DisplayWaitingQueue();
        }

        ViewWaitingQueue.setText(WaitingQueueDetails);
    }


    @FXML
    //get search value and display results
    protected void SearchPassenger(){
        String input_name=search_input.getText();
        FuelQueue queue[][] = FuelQueue.getFuelQueue();
        FuelQueue waiting[]=WaitingList.getWaiting_list();
        boolean isNameEqual=false;
        String output_text="";

            for (int row=0;row< queue.length;row++){
                for (int col=0;col< queue[row].length;col++){
                    if (queue[row][col]!=null && ((queue[row][col].getFirstName()).equalsIgnoreCase(input_name))){
                        output_text+=queue[row][col].getFirstName()+"'s Details:";
                        output_text+="\n\tFirst Name: "+queue[row][col].getFirstName();
                        output_text+="\n\tSecond Name: "+queue[row][col].getSecondName();
                        output_text+="\n\tVehicle No: "+queue[row][col].getVehicleNo();
                        output_text+="\n\tLiters Required: "+queue[row][col].getLitersRequired();
                        isNameEqual=true;
                        break;
                    }
                }
                if (isNameEqual==true){
                    break;
                }
            }
            for (int i=0;i< waiting.length;i++){
                if (waiting[i]!=null && ((waiting[i].getFirstName()).equalsIgnoreCase(input_name))){
                    output_text+=waiting[i].getFirstName()+"'s Details:";
                    output_text+="\n\tFirst Name: "+waiting[i].getFirstName();
                    output_text+="\n\tSecond Name: "+waiting[i].getSecondName();
                    output_text+="\n\tVehicle No: "+waiting[i].getVehicleNo();
                    output_text+="\n\tLiters Required: "+waiting[i].getLitersRequired();
                    isNameEqual=true;
                    break;
                }
            }
            if (isNameEqual==false){
                output_text+=("Entered name can't be found in fuel queue");
            }

        search_results.setText(output_text);
    }

    //display fuel income
    @FXML
    protected void DisplayFuelIncome(){
        String DisplayPumpIncome="";
        int TotalIncome=0;
        int[] IncomeList=FuelQueue.getFuelIncome();
        for (int i=0;i<IncomeList.length;i++){
            DisplayPumpIncome+="Fuel Pump No "+(i+1)+" Income-> "+IncomeList[i]+"\n";
            TotalIncome+=IncomeList[i];
        }
        FuelPumpIncome.setText(DisplayPumpIncome);
        TotalFuelIncome.setText("Total income is, "+TotalIncome);
    }
}
