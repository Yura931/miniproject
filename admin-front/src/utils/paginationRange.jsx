import _ from "lodash";
export const paginationRange = (totalPage, page, limit, siblings) => {
    let totalPageNoInArray = 10;

    if (totalPageNoInArray >= totalPage) {
        return _.range(1, totalPage);
    }

    let leftSiblingsIndex = Math.max(page + 1 - siblings, 1)
    let rightSiblingsIndex = Math.min(page + siblings , totalPage);

    let showLeftDots = leftSiblingsIndex > 2
    let showRightDots = rightSiblingsIndex < totalPage -1

    if(!showLeftDots && showRightDots) {

        let leftItemsCount = 3 + 2 * siblings;
        let leftRange = _.range(1, leftItemsCount + 1);
        return [...leftRange, " ...", totalPage];

    } else if (showLeftDots && !showRightDots) {

        let rightItemsCount = 3 + 2 * siblings;
        let rightRange = _.range(totalPage - rightItemsCount + 1, totalPage);
        return [1, "... ", ...rightRange, totalPage];

    } else {

        let middleRange = _.range(leftSiblingsIndex, rightSiblingsIndex + 1);
        return [1, "... ", ...middleRange, " ...", totalPage];
    }
}