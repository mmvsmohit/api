package com.xyzinnotech.projectplan.service;

import java.io.FileWriter;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.xyzinnotech.projectplan.pojo.JSGantt;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Relation;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;

public class ProjectReader {

	private static FileWriter fileWriter;

	public String mppToJson(InputStream is) {
		MPPReader reader = new MPPReader();
		reader.setPreserveNoteFormatting(true);
		reader.setReadPresentationData(true);
		//ArrayList<Activity> activitiesList = new ArrayList<>();
		ArrayList<JSGantt> jsGanttsList = new ArrayList<>();
		//ArrayList<GoogleChart> googleChart = new ArrayList<>();
		try {
			//ProjectFile project = reader.read("src/main/resources/vangani.mpp");
			ProjectFile project = reader.read(is);
			
			
//			for (Task task : project.getAllTasks()) {
//				activitiesList.add(getActivityForTask(task));
//			}
			for (Task task : project.getAllTasks()) {
				jsGanttsList.add(getJSGanttObject(task));
			}
//			for (Task task : project.getAllTasks()) {
//				googleChart.add(getGoogleChartObject(task));
//			}
			System.out.println(project.getAllTasks().size());
			// for (int i = 0; i < 2000; i++) {
			// System.out.println(getActivityForTask(project.getAllTasks().get(i)));
			// }
			// getChildTask(project.getAllTasks().get(0), 0, parent);
		} catch (MPXJException e) {
			e.printStackTrace();
		} 
		
		/*try {
			fileWriter = new FileWriter("file.json");
			fileWriter.write(jsGanttsList.toString());
			System.out.println("File Write Complete");
		} catch (Exception e) {
			System.out.println("File Write Exception");
			e.printStackTrace();
		}
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return jsGanttsList.toString();
		
		
	}

	/*private static void getChildTask(Task task, int level, Activity parent) {
		List<Task> childTasks = task.getChildTasks();
		Activity mActivity = getActivityForTask(task);
//		parent.addChildActivity(mActivity);
		if (childTasks != null && !childTasks.isEmpty()) {
			for (Task t : childTasks) {
				getChildTask(t, level + 1, mActivity);
			}
		}
	}

	private static Activity getActivityForTask(Task task) {
		Activity mActivity = new Activity();
		mActivity.setName(task.getName());
		mActivity.setPlannedStart(task.getStart());
		mActivity.setPlannedFinish(task.getFinish());
		mActivity.setDuration(task.getDuration().getDuration());
		mActivity.setWbsId(task.getWBS());
		mActivity.setProject("5ac49f35a3753144ceca19c6");
		mActivity.setCreated_by("5acdd212fdc9544a69ba94e0");
		mActivity.setOwner("5acdd212fdc9544a69ba94e0");
		List<Relation> predecessors = task.getPredecessors();
		if (predecessors != null && predecessors.isEmpty() == false) {
			ArrayList<Activity.Predecessor> predecessorsList = new ArrayList<>();
			for (Relation relation : predecessors) {
				Activity.Predecessor predecessor = new Activity.Predecessor();
				predecessor.setWbsId(relation.getTargetTask().getWBS());
				predecessor.setRelation(getTrimmedRelation(relation.getType().name()));
				predecessor.setOffset(relation.getLag().getDuration());
				predecessorsList.add(predecessor);
			}
			mActivity.setPredecessor(predecessorsList);
		}
		return mActivity;
	}*/
	
	private static JSGantt getJSGanttObject(Task task) {
		JSGantt jsGantt = new JSGantt();
		jsGantt.setpID(task.getID());
		jsGantt.setpName(task.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		jsGantt.setpStart(sdf.format(task.getStart()));
		jsGantt.setpEnd(sdf.format(task.getFinish()));
		jsGantt.setpClass(task.getChildTasks().isEmpty() ? "gtaskblue" : "ggroupblack");
		jsGantt.setpGroup(task.getChildTasks().isEmpty() ? 0 : 1);
		if(task.getParentTask() != null) {
			jsGantt.setpParent(task.getParentTask().getID());
		}
		
		String dependents = "";
		List<Relation> predecessors = task.getPredecessors();
		if (predecessors != null && predecessors.isEmpty() == false) {
			for (Relation relation : predecessors) {
				dependents += relation.getTargetTask().getID() + getTrimmedRelation(relation.getType().name()) + ",";
			}
		}
		
		jsGantt.setpDepend(dependents);
		return jsGantt;
	}
	
	/*private static GoogleChart getGoogleChartObject(Task task) {
		GoogleChart googleChart = new GoogleChart();
		googleChart.setWbs_id(task.getWBS());
		googleChart.setName(task.getName());
		googleChart.setStart(task.getStart());
		googleChart.setFinish(task.getFinish());
		googleChart.setDuration(task.getDuration().getDuration());
		googleChart.setProgress(task.getPercentageComplete().doubleValue());
		String dependents = "";
		List<Relation> predecessors = task.getPredecessors();
		if (predecessors != null && predecessors.isEmpty() == false) {
			for (Relation relation : predecessors) {
				dependents += relation.getTargetTask().getWBS() + ",";
			}
		}
		if(dependents.endsWith(",")) {
			dependents = dependents.substring(0, dependents.length() - 1);
		}
		
		googleChart.setDependencies(dependents);
		return googleChart;
	}*/

	private static String getTrimmedRelation(String relationType) {
		switch (relationType) {
		case "FIFISH_START":
			return "FS";
		case "START_START":
			return "SS";
		case "FIFISH_FINISH":
			return "FF";
		case "START_FINISH":
			return "SF";
		default:
			return "";
		}
	}
}
