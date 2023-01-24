import React from "react";

const Mine = ({ mine, rowIndex, columnIndex, pressSquare }) => {
	return (
		<td>
			<button onClick={() => pressSquare(rowIndex, columnIndex)}>
				{mine}
			</button>
		</td>
	);
};

export default Mine;
