import React from "react";
import { useState, useEffect } from "react";

const Mine = ({ mine, rowIndex, columnIndex, pressSquare, alive, refresh }) => {
	/**
	 * Is mine and exploded: -2
	 * Is unknown -1
	 * Is known and 0 adjacent 0
	 * Is known and 1 or more adjacent n
	 */
	const [mineStringRep, setmineStringRep] = useState(" ");
	const [flagged, setFlagged] = useState(false);
	useEffect(() => {
		if (mine === -2) {
			setmineStringRep("*");
			setFlagged(false);
		} else if (mine === -1) {
			if (flagged) {
				setmineStringRep("!!");
			} else {
				setmineStringRep(" ");
			}
		} else if (mine === 0) {
			setmineStringRep(" ");
			setFlagged(false);
		} else {
			setmineStringRep(mine.toString());
			setFlagged(false);
		}
	}, [mine, flagged]);
	useEffect(() => {
		if (alive) {
			setFlagged(false);
		}
	}, [refresh, alive]);

	const rightClick = (e) => {
		e.preventDefault();
		setFlagged(!flagged);
	};
	return (
		<td className={mine === -2 ? "dead" : ""}>
			<button
				onClick={() => pressSquare(rowIndex, columnIndex)}
				onContextMenu={(e) => rightClick(e)}
				disabled={mine !== -1 || !alive}
			>
				{mineStringRep}
			</button>
		</td>
	);
};

export default Mine;
