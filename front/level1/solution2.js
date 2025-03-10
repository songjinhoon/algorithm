const solution = (new_id) => {
    let answer = new_id
        .toLowerCase()
        .replace(/[^\w-_.]/g, '')
        .replace(/\.+/g, '.')
        .replace(/^\.|\.$/g, '')
        .replace(/^$/, 'a')
        .slice(0, 15)
        .replace(/\.$/, '')

    answer = (() => {
        const lastValue = answer[answer.length - 1]
        const repeatLength = answer.length <= 2 ? 3 - answer.length : 0
        const newValue = lastValue.repeat(repeatLength)
        return answer + newValue
    })()

    return answer
}

const checkId1 = "...!@BaT#*..y.abcdefghijklm"
const checkId2 = "z-+.^."
const checkId3 = "=.="
const checkId4 = "123_.def"
const checkId5 = "abcdefghijklmn.p"

solution(checkId1)
solution(checkId2)
solution(checkId3)
solution(checkId4)
solution(checkId5)