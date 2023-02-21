import React from "react";

const Cell = ({ cellValue, playMove, rowIndex, columnIndex }) => {
	return (
		<button onClick={() => playMove()}>
			{cellValue ? cellValue : " "}
		</button>
	);
};

export default Cell;
