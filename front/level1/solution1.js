/* 바탕화면 정리 */
function solution(wallpaper) {
    const startPoint = []// 제일 위에있는 친구 (x), 제일 왼쪽에있는 친구(y)
    const endPoint = []// 제일 밑에있는 친구 (x), 제일 오른쪽에있는 친구 (y)
    const size = wallpaper.length
    const boxSize = wallpaper[0].split('').length
    const pixels = wallpaper.map(item => item.split(''))

    for (let i=0;i<size;i++) {
        for (let k=0;k<boxSize;k++) {
            if(pixels[i][k] === '#') {
                if (startPoint[0] === undefined) {
                    startPoint[0] = i
                }
                endPoint[0] = i + 1
            }
        }
    }

    for (let i=0;i<boxSize;i++) {
        for (let k=0;k<size;k++) {
            if(pixels[k][i] === '#') {
                if (startPoint[1] === undefined) {
                    startPoint[1] = i
                }
                endPoint[1] = i + 1
            }
        }
    }

    return [...startPoint, ...endPoint];
}

const wallpaper = [".#...", "..#..", "...#."]

console.log(solution(wallpaper))