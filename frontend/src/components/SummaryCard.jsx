import { CCard, CCardBody } from '@coreui/react'

const SummaryCard = ({ title, value }) => {
  return (
    <CCard>
      <CCardBody>
        <div className="text-medium-emphasis">{title}</div>
        <div className="fs-3 fw-semibold">{value}</div>
      </CCardBody>
    </CCard>
  )
}

export default SummaryCard
