import { PieChart } from "react-minimal-pie-chart";
import React, { useState } from "react";
import MicroModal from "react-micro-modal";
import { GoGraph } from "react-icons/go";
import { RiCloseCircleLine } from "react-icons/ri";
import { startFetchDeleted_count, startFetchList_todos } from "./GraphCall";
import { context } from "./context";

function PieChartGraph() {
  const [dataMock, setDataMock] = useState([
    { title: "Completed", value: 10, color: "#6A2135" },
    { title: "In-progress", value: 15, color: "#C13C37" },
  ]);

  const loadData = async () => {
    if (context.deleted === 0) {
      context.deleted = 1;
      const promise1 = startFetchDeleted_count();
      const promise2 = startFetchList_todos();
      const data = await Promise.all([promise1, promise2]);

      const dataFromPromise1 = data[0];
      const dataFromPromise2 = data[1];

      let newdata = [
        { title: "Completed", value: 10, color: "#000" },
        { title: "In-progress", value: 15, color: "#C13C37" },
      ];

      //add into the remote
      if (
        dataFromPromise1 !== undefined &&
        dataFromPromise1.todo_stats !== undefined
      ) {
        context.deleted = dataFromPromise1.todo_stats[0].counter;
      }

      if (
        dataFromPromise2 !== undefined &&
        dataFromPromise2.todo !== undefined
      ) {
        let tr = 0;
        let fr = 0;
        dataFromPromise2.todo.forEach((e) => {
          e.todo_complete ? tr++ : fr++;
        });
        newdata[0].value = tr;
        newdata[1].value = fr;
      }

      setDataMock(newdata);
    }
    setDataMock(dataMock);
  };

  const defaultLabelStyle = {
    fontSize: "5px",
    cursor: "pointer",
    fill: "#fff",
  };
  const shiftSize = 7;

  return (
    <MicroModal
      trigger={(handleOpen) => (
        <button className="graph-button" onClick={(loadData(), handleOpen)}>
          <GoGraph></GoGraph>
        </button>
      )}
      children={(handleClose) => (
        <>
          <RiCloseCircleLine onClick={handleClose} className="close-icon" />
          <span>Total deleted tasks: {context.deleted}</span>
          <PieChart
            data={dataMock}
            radius={PieChart.defaultProps.radius - shiftSize}
            segmentsShift={2} //(index) =>(index === 0 ? shiftSize : 0.5)
            // label={({ dataEntry }) => `${Math.round(dataEntry.percentage)} %`}
            label={({ dataEntry }) => dataEntry.title}
            labelStyle={{
              ...defaultLabelStyle,
            }}
            animationEasing={"ease-out"}
            animate={true}
            animationDuration={500}
            lineWidth={80}
            // paddingAngle={10}
          />
        </>
      )}
    />
  );
}

export { PieChartGraph };
