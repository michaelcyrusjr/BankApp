import { CListGroupItem } from '@coreui/react'

const TransactionRow = ({ name, date, amount }) => {
  return (
    <CListGroupItem className="px-0 py-3">
      <div className="d-flex justify-content-between">
        <div>
          <div className="fw-semibold">{name}</div>
          <div className="fw-medium-emphasis small">{date}</div>
        </div>
        <div className="fw-semibold">{amount}</div>
      </div>
    </CListGroupItem>
  )
}

export default TransactionRow
